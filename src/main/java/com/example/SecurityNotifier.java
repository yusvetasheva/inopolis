package com.example;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SecurityNotifier implements ApplicationListener<StopFactorCheckEvent> {
    @Override
    public void onApplicationEvent(StopFactorCheckEvent event) {
        if (event.hasStopFactors()) {
            // Отправка уведомления СБ
            System.out.println("Уведомление СБ: клиент " + event.getClientId() + " заблокирован");
        }
    }
}
