package com.example;

import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDecorator extends StopFactorDecorator {

    public CreditHistoryDecorator(StopFactorCheck decoratedCheck) {
        super(decoratedCheck);
    }

    @Override
    public boolean check(String clientId) {
        return super.check(clientId) || ExternalCreditBureau.getCreditScore(clientId) < 500;
    }
}

