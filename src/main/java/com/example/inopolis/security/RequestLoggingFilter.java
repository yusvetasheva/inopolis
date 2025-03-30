package com.example.inopolis.security;

import com.example.inopolis.model.entity.LogEntry;
import com.example.inopolis.repository.LogRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.time.LocalDateTime;

@Component
public class RequestLoggingFilter implements Filter {

    private final LogRepository logRepository;

    public RequestLoggingFilter(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String method = httpRequest.getMethod();
            String uri = httpRequest.getRequestURI();
            String authorizationHeader = httpRequest.getHeader("Authorization");

            String decodedAuth = (authorizationHeader != null && authorizationHeader.startsWith("Basic "))
                    ? decodeBase64(authorizationHeader.substring(6))
                    : "N/A";

            // Выводим лог в консоль
            System.out.println("Incoming Request: Method = " + method + ", URI = " + uri);
            if (authorizationHeader != null) {
                System.out.println("Authorization Header: " + authorizationHeader);
                System.out.println("Decoded Authorization: " + decodedAuth);
            }

            // Сохраняем лог в базу данных
            LogEntry logEntry = LogEntry.builder()
                    .method(method)
                    .uri(uri)
                    .authorizationHeader(authorizationHeader)
                    .decodedAuthorization(decodedAuth)
                    .timestamp(LocalDateTime.now())
                    .build();

            logRepository.save(logEntry);
        }

        chain.doFilter(request, response);
    }

    private String decodeBase64(String encoded) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return "Invalid Base64 encoding";
        }
    }
}
