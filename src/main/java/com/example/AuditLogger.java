package com.example;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogger implements ApplicationListener<StopFactorCheckEvent> {
    @Override
    public void onApplicationEvent(StopFactorCheckEvent event) {
        System.out.println("Аудит: клиент " + event.getClientId() + " проверен, стоп-факторы: " + event.hasStopFactors());
    }
}



