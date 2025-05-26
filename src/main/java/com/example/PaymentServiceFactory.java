package com.example;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory {

    public PaymentService createPaymentService(String type) {
        if ("bank".equalsIgnoreCase(type)) {
            return new PaymentService(new BankPaymentProcessor());
        } else if ("crypto".equalsIgnoreCase(type)) {
            return new PaymentService(new CryptoPaymentProcessor());
        }
        throw new IllegalArgumentException("Неизвестный тип платежа: " + type);
    }
}

