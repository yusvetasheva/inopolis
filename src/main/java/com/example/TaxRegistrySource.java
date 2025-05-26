package com.example;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaxRegistrySource implements StopFactorSource {

    @Override
    public boolean hasStopFactors(String identifier) {
        Map<String, String> data = ExternalTaxRegistry.getCompanyStatus(identifier);
        return "BLACKLISTED".equals(data.get("status"));
    }
}

