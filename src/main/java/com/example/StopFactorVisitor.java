package com.example;

public interface StopFactorVisitor {
    boolean visit(CreditHistoryStopFactor creditHistoryStopFactor);

    boolean visit(AgeStopFactor ageStopFactor);
}

