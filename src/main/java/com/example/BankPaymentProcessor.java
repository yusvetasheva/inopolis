package com.example;

public class BankPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка банковского платежа: " + amount);
    }
}
