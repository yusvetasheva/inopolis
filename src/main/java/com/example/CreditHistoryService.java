package com.example;

import org.springframework.stereotype.Component;

@Component
public class CreditHistoryService {

    public boolean hasBadCreditHistory(String clientId) {
        return ExternalCreditBureau.getCreditScore(clientId) < 500;
    }
}
