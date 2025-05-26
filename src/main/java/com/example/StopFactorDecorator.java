package com.example;

public abstract class StopFactorDecorator implements StopFactorCheck {

    protected final StopFactorCheck decoratedCheck;

    public StopFactorDecorator(StopFactorCheck decoratedCheck) {
        this.decoratedCheck = decoratedCheck;
    }

    @Override
    public boolean check(String clientId) {
        return decoratedCheck.check(clientId);
    }
}

