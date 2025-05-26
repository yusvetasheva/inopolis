package com.example;

import org.springframework.context.ApplicationContext;

public class CheckedState implements ApplicationState {
    @Override
    public void handle(ApplicationContext context) {
        System.out.println("Заявка одобрена, можно продолжать обработку.");
    }
}

