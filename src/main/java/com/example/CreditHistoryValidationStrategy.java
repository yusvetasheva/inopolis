package com.example;

public class CreditHistoryValidationStrategy implements StopFactorValidationStrategy {
    @Override
    public boolean validate(Customer customer) {
        // Логика для проверки кредитной истории
        return !customer.hasBadCreditHistory();
    }
}

