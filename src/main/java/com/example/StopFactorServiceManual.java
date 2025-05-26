package com.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class StopFactorServiceManual {

    private final PlatformTransactionManager transactionManager;

    public StopFactorServiceManual(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void processStopFactor(String companyName) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            System.out.println("Старт транзакции...");
            System.out.println("Проверка стоп-факторов для компании: " + companyName);
            System.out.println("Завершение транзакции...");
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}

