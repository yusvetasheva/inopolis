package com.example;

import org.springframework.stereotype.Component;

@Component
public class LegalEntityChecker extends StopFactorChecker {

    public LegalEntityChecker(TaxRegistrySource taxRegistry) {
        super(taxRegistry); // Используем налоговый реестр
    }

    @Override
    public boolean check(String identifier) {
        return source.hasStopFactors(identifier);
    }
}


