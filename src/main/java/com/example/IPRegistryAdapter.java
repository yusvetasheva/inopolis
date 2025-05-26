package com.example;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IPRegistryAdapter implements StopFactorChecker {

    @Override
    public boolean hasStopFactors(String companyName) {
        Map<String, String> ipData = ExternalIPRegistry.getData(companyName);
        return "BLOCKED".equals(ipData.get("status"));
    }
}

