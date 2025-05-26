package com.example;

import org.springframework.stereotype.Component;

@Component("bankFactory")
public class BankPaymentFactory implements PaymentFactory {
    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new BankPaymentProcessor();
    }
}

