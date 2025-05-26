package com.example;

import org.springframework.stereotype.Component;

@Component
public class IndividualChecker extends StopFactorChecker {

    public IndividualChecker(CreditBureauSource creditBureau) {
        super(creditBureau); // Используем кредитное бюро
    }

    @Override
    public boolean check(String identifier) {
        return source.hasStopFactors(identifier);
    }
}

