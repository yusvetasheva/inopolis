package com.example;


import org.springframework.stereotype.Component;

@Component
public class CreditHistoryCheck implements StopFactorChecker {

    @Override
    public boolean check(Client client) {
        // Проверка кредитной истории
        return client.getCreditHistory();
    }
}


