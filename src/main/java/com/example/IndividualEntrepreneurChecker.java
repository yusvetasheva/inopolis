package com.example;

public class IndividualEntrepreneurChecker {
    private final TaxRegistry taxRegistry = new TaxRegistry();
    private final CourtCases courtCases = new CourtCases();

    public boolean hasStopFactors(String companyName) {
        return taxRegistry.isBlacklisted(companyName) || courtCases.hasUnpaidDebts(companyName);
    }
}

