package com.example;

public class BlacklistAndCreditCheck implements StopFactorCheck {

    private final BlacklistCheck blacklistCheck;
    private final CreditHistoryCheck creditHistoryCheck;

    public BlacklistAndCreditCheck(BlacklistCheck blacklistCheck, CreditHistoryCheck creditHistoryCheck) {
        this.blacklistCheck = blacklistCheck;
        this.creditHistoryCheck = creditHistoryCheck;
    }

    @Override
    public boolean check(String clientId) {
        return blacklistCheck.check(clientId) || creditHistoryCheck.check(clientId);
    }
}

