package com.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompositeStopFactorCheck implements StopFactorCheck {

    private final List<StopFactorCheck> checks = new ArrayList<>();

    public void addCheck(StopFactorCheck check) {
        checks.add(check);
    }

    @Override
    public boolean check(String clientId) {
        return checks.stream().anyMatch(check -> check.check(clientId));
    }
}

