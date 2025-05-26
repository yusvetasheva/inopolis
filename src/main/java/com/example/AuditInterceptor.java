package com.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
public class AuditInterceptor extends OncePerRequestFilter {

    private UserRepository stopFactorService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String username = request.getUserPrincipal() != null
                ? request.getUserPrincipal().getName()
                : "ANONYMOUS";

        String uri = request.getRequestURI();

        System.out.println("User " + username + " accessed URI: " + uri);

        filterChain.doFilter(request, response);
    }


    @GetMapping("/stop-factors")
    public ResponseEntity<List<StopFactor>> getStopFactors() throws AccessDeniedException {
        if (!userHasPermission()) {
            throw new AccessDeniedException("Недостаточно прав");
        }
        logAccess();
        return ResponseEntity.ok(stopFactorService.findAll());
    }

    private void logAccess() {

    }

    private boolean userHasPermission() {
        return false;
    }

}

