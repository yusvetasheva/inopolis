package com.example;

public class AgeValidationStrategy implements StopFactorValidationStrategy {
    @Override
    public boolean validate(Customer customer) {
        // Логика для проверки возраста
        return customer.getAge() >= 18;
    }
}

