package com.example;

import org.springframework.stereotype.Component;

@Component
public class BlacklistCheck implements StopFactorCheck {

    @Override
    public boolean check(String clientId) {
        return ExternalBlacklistService.isBlacklisted(clientId);
    }
}



