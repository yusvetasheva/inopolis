package com.example;

public class BlacklistCheckCommand implements StopFactorCheckCommand {
    private final String clientId;
    private final StopFactorService stopFactorService;

    public BlacklistCheckCommand(String clientId, StopFactorService stopFactorService) {
        this.clientId = clientId;
        this.stopFactorService = stopFactorService;
    }

    @Override
    public void execute() {
        if (stopFactorService.isBlacklisted(clientId)) {
            System.out.println("Клиент " + clientId + " в чёрном списке. Отказ в кредитовании.");
        } else {
            System.out.println("Клиент " + clientId + " не в чёрном списке.");
        }
    }
}

