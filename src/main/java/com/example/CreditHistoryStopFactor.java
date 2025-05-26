package com.example;

public class CreditHistoryStopFactor implements StopFactor {
    private boolean hasBadHistory;

    public CreditHistoryStopFactor(boolean hasBadHistory) {
        this.hasBadHistory = hasBadHistory;
    }

    @Override
    public boolean accept(StopFactorVisitor visitor) {
        return visitor.visit(this);
    }

    public boolean hasBadHistory() {
        return hasBadHistory;
    }
}

