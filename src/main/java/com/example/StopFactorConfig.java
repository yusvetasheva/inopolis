package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StopFactorConfig {

    @Bean
    public StopFactorCheck stopFactorCheck() {
        StopFactorCheck baseCheck = clientId -> false; // Базовая пустая проверка
        StopFactorCheck blacklistCheck = new BlacklistDecorator(baseCheck);
        return new CreditHistoryDecorator(blacklistCheck);
    }
}


