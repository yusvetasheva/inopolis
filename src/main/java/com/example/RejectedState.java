package com.example;

import org.springframework.context.ApplicationContext;

public class RejectedState implements ApplicationState {
    @Override
    public void handle(ApplicationContext context) {
        System.out.println("Заявка отклонена. Обнаружены стоп-факторы.");
    }
}

