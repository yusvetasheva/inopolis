package com.example;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John", 25, false);

        // Использование стратегии проверки возраста
        StopFactorProcessor processor = new StopFactorProcessor();
        processor.setValidationStrategy(new AgeValidationStrategy());
        System.out.println("Age validation: " + processor.validateStopFactor(customer)); // true

        // Использование стратегии проверки кредитной истории
        processor.setValidationStrategy(new CreditHistoryValidationStrategy());
        System.out.println("Credit history validation: " + processor.validateStopFactor(customer)); // true
    }

}
}
