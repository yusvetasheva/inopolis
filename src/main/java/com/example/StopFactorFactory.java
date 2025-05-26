package com.example;

import java.util.HashMap;
import java.util.Map;

public class StopFactorFactory {
    private static final Map<String, StopFactor> stopFactorPool = new HashMap<>();

    public static StopFactor getStopFactor(String name, String description) {
        stopFactorPool.putIfAbsent(name, new StopFactor(name, description));
        return stopFactorPool.get(name);
    }

    public static int getPoolSize() {
        return stopFactorPool.size();
    }
}

