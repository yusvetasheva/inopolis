package com.example;

public class CreditHistoryStopFactorValidation extends StopFactorValidation {

    @Override
    protected boolean performSpecificCheck(Customer customer) {
        // Конкретная реализация проверки кредитной истории
        return !customer.hasBadCreditHistory();
    }
}

