package com.example;

public class StopFactorValidationVisitor implements StopFactorVisitor {

    @Override
    public boolean visit(CreditHistoryStopFactor creditHistoryStopFactor) {
        // Логика проверки кредитной истории
        return !creditHistoryStopFactor.hasBadHistory(); // Не допускаем плохую кредитную историю
    }

    @Override
    public boolean visit(AgeStopFactor ageStopFactor) {
        // Логика проверки возраста
        return ageStopFactor.getAge() >= 18; // Должен быть совершеннолетним
    }
}

