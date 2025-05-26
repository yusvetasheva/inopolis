package com.example;

import java.util.ArrayList;
import java.util.List;

public class LoanApplication {
    private final String applicantName;
    private final List<StopFactor> stopFactors = new ArrayList<>();

    public LoanApplication(String applicantName) {
        this.applicantName = applicantName;
    }

    public void addStopFactor(String name, String description) {
        StopFactor stopFactor = StopFactorFactory.getStopFactor(name, description);
        stopFactors.add(stopFactor);
    }

    public void printStopFactors() {
        System.out.println("Заявка на кредит: " + applicantName);
        for (StopFactor sf : stopFactors) {
            System.out.println("- " + sf.getName() + ": " + sf.getDescription());
        }
    }
}

