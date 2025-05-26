package com.example;

public class CryptoPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Обработка криптовалютного платежа: " + amount);
    }
}

