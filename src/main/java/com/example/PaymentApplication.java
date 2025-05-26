package com.example;

public class PaymentApplication {
    public static void main(String[] args) {
        BankPaymentProcessor processor = new BankPaymentProcessor();
        PaymentService paymentService = new PaymentService(processor);
        paymentService.processPayment(1000);
    }
}

