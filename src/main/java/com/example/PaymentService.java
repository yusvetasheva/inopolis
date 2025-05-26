package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    public PaymentService() {
        this.paymentProcessor = new BankPaymentProcessor(); // Жесткая привязка
    }

    public void process(double amount) {
        paymentProcessor.processPayment(amount);
    }
}

