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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/",
                        "/v3/api-docs/",
                        "/swagger-ui.html",
                        "/v3/api-docs.yaml").permitAll()

                //.requestMatchers("/swagger-ui/",
                //                        "/v3/api-docs/",
                //                        "/swagger-ui.html",
                //                        "/v3/api-docs.yaml").permitAll()
                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("VIEWER", "USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated() //Пользователи должны быть авторизованы, но роль не проверяется
                .and().httpBasic();

        return http.build();

    }

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
