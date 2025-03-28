package com.example.inopolis.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashPasswordService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**Сервис хэширует наш пароль с солью.
     * BCryptPasswordEncoder автоматически добавляет соль*/
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
