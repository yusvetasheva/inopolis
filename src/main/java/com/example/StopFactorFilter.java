package com.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class StopFactorFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Имитируем проверку стоп-факторов
        String clientId = httpRequest.getHeader("Client-Id");
        if ("blacklisted-client".equals(clientId)) {
            throw new ServletException("Отказ в обслуживании: клиент в черном списке!");
        }

        // Передаём запрос дальше по цепочке
        chain.doFilter(request, response);
    }
}
