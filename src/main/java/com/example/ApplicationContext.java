package com.example;

public class ApplicationContext {
    private ApplicationState state;
    private boolean stopFactors;

    public ApplicationContext(boolean stopFactors) {
        this.stopFactors = stopFactors;
        this.state = new PendingCheckState();
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

    public boolean hasStopFactors() {
        return stopFactors;
    }

    public void process() {
        state.handle((org.springframework.context.ApplicationContext) this);
    }
}

