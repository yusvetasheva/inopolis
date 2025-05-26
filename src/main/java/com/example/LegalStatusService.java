package com.example;

import org.springframework.stereotype.Component;

@Component
public class LegalStatusService {

    public boolean isLegalEntitySuspicious(String clientId) {
        return LegalDatabase.isUnderInvestigation(clientId);
    }
}

