package com.example;

import org.springframework.stereotype.Component;

@Component
public class CreditBureauSource implements StopFactorSource {

    @Override
    public boolean hasStopFactors(String identifier) {
        return ExternalCreditBureau.getCreditInfo(identifier).contains("HIGH_RISK");
    }
}

