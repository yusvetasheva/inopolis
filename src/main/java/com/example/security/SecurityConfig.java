package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности приложения.
 * <p>
 * Настраивает HTTP-безопасность с использованием ролей и базовой аутентификации.
 * Также определяет пользователей в памяти с различными ролями:
 * <ul>
 *     <li>{@code ADMIN}</li>
 *     <li>{@code USER}</li>
 *     <li>{@code VIEWER}</li>
 * </ul>
 * </p>
 *
 * <p><b>Правила доступа:</b></p>
 * <ul>
 *     <li>GET-запросы — разрешены для ролей VIEWER, USER, ADMIN</li>
 *     <li>POST, DELETE — только для ADMIN</li>
 *     <li>PUT — для USER и ADMIN</li>
 *     <li>Все остальные запросы — требуют авторизации</li>
 * </ul>
 *
 * @author Иван
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("VIEWER", "USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated() //Пользователи должны быть авторизованы, но роль не проверяется
                .and().httpBasic();

        return http.build();

    }

    /**
     * Определяет пользователей и их роли в памяти.
     *
     * @return {@link UserDetailsService} с предопределёнными пользователями
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("{noop}123")
                .roles("USER")
                .build()
        );
        manager.createUser(User.withUsername("admin")
                .password("{noop}123")
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("viewer")
                .password("{noop}123")
                .roles("VIEWER")
                .build());

        return manager;
    }
}
