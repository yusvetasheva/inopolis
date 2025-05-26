package com.example;

import java.util.List;

public class StopFactorProcessor {

    public boolean validateAgeStopFactor(Customer customer) {
        return customer.getAge() >= 18;
    }

    public boolean validateCreditHistoryStopFactor(Customer customer) {
        return !customer.hasBadCreditHistory();
    }

    public boolean validateAllStopFactors(Customer customer) {
        return validateAgeStopFactor(customer) && validateCreditHistoryStopFactor(customer);
    }
}





