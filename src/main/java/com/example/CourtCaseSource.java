package com.example;

import org.springframework.stereotype.Component;

@Component
public class CourtCaseSource implements StopFactorSource {

    @Override
    public boolean hasStopFactors(String identifier) {
        return ExternalCourtCases.hasUnpaidDebts(identifier);
    }
}

