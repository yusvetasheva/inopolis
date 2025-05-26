package com.example;

import org.springframework.stereotype.Component;

@Component
public class EntrepreneurChecker extends StopFactorChecker {

    public EntrepreneurChecker(CourtCaseSource courtCases) {
        super(courtCases); // Используем суды
    }

    @Override
    public boolean check(String identifier) {
        return source.hasStopFactors(identifier);
    }
}

