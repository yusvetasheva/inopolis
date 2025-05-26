package com.example;

import org.springframework.stereotype.Component;

@Component
public class StopFactorFacade {

    private final BlacklistService blacklistService;
    private final CreditHistoryService creditHistoryService;
    private final LegalStatusService legalStatusService;

    public StopFactorFacade(BlacklistService blacklistService,
                            CreditHistoryService creditHistoryService,
                            LegalStatusService legalStatusService) {
        this.blacklistService = blacklistService;
        this.creditHistoryService = creditHistoryService;
        this.legalStatusService = legalStatusService;
    }

    public boolean hasStopFactors(String clientId) {
        return blacklistService.isBlacklisted(clientId)
                || creditHistoryService.hasBadCreditHistory(clientId)
                || legalStatusService.isLegalEntitySuspicious(clientId);
    }
}

