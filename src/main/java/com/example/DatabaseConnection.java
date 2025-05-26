package com.example;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
    public DatabaseConnection() {
        System.out.println("Подключение к базе данных создано");
    }
}

