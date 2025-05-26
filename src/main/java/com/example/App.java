package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

public class App {
    public static void main(String[] args) {
        LoanApplication app1 = new LoanApplication("ООО 'Ромашка'");
        app1.addStopFactor("Фиктивное юр. лицо", "Компания зарегистрирована по массовому адресу");
        app1.addStopFactor("Отказ по 115-ФЗ", "Подозрительные транзакции");

        LoanApplication app2 = new LoanApplication("ИП Иванов");
        app2.addStopFactor("Фиктивное юр. лицо", "Компания зарегистрирована по массовому адресу"); // Уже существует в пуле!

        app1.printStopFactors();
        app2.printStopFactors();

        System.out.println("Количество созданных объектов StopFactor: " + StopFactorFactory.getPoolSize());
    }
}
