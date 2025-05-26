package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final StopFactorFilter stopFactorFilter;

    public SecurityConfig(StopFactorFilter stopFactorFilter) {
        this.stopFactorFilter = stopFactorFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/loan-request").authenticated() // Доступ только аутентифицированным пользователям
                        .anyRequest().permitAll()
                )
                .addFilterBefore(stopFactorFilter, UsernamePasswordAuthenticationFilter.class) // Добавляем кастомный фильтр
                .build();
    }
}

