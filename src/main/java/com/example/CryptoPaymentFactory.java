package com.example;

import org.springframework.stereotype.Component;

@Component("cryptoFactory")
public class CryptoPaymentFactory implements PaymentFactory {
    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new CryptoPaymentProcessor();
    }
}

