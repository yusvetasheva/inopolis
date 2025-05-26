package com.example;

import org.springframework.context.ApplicationEvent;

public class StopFactorCheckEvent extends ApplicationEvent {
    private final String clientId;
    private final boolean hasStopFactors;

    public StopFactorCheckEvent(Object source, String clientId, boolean hasStopFactors) {
        super(source);
        this.clientId = clientId;
        this.hasStopFactors = hasStopFactors;
    }

    public String getClientId() {
        return clientId;
    }

    public boolean hasStopFactors() {
        return hasStopFactors;
    }
}

