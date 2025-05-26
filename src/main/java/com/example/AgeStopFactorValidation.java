package com.example;

public class AgeStopFactorValidation extends StopFactorValidation {

    @Override
    protected boolean performSpecificCheck(Customer customer) {
        // Конкретная реализация проверки возраста
        return customer.getAge() >= 18;
    }
}
