package com.example;

import org.springframework.stereotype.Component;

@Component
public class BlacklistDecorator extends StopFactorDecorator {

    public BlacklistDecorator(StopFactorCheck decoratedCheck) {
        super(decoratedCheck);
    }

    @Override
    public boolean check(String clientId) {
        return super.check(clientId) || ExternalBlacklistService.isBlacklisted(clientId);
    }
}

