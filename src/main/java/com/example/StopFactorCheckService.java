package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StopFactorCheckService {

    private final List<StopFactorChecker> checkers;

    @Autowired
    public StopFactorCheckService(List<StopFactorChecker> checkers) {
        this.checkers = checkers;
    }

    public boolean checkStopFactors(IntegrationProperties.RSocket.Client client) {
        for (StopFactorChecker checker : checkers) {
            if (!checker.check(String.valueOf(client))) {
                return false; // Стоп-фактор обнаружен
            }
        }
        return true; // Нет стоп-факторов
    }

    public boolean checkStopFactors(Client client) {
        return false;
    }
}

