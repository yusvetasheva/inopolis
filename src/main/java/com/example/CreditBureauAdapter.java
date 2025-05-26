package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CreditBureauAdapter implements StopFactorChecker {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean hasStopFactors(String companyName) {
        String jsonResponse = ExternalCreditBureau.getCreditInfo(companyName);
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            return "HIGH".equals(rootNode.get("riskLevel").asText());
        } catch (Exception e) {
            return false;
        }
    }
}
