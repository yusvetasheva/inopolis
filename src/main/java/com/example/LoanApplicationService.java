package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanApplicationService {

    private final List<StopFactorCheckCommand> stopFactorCommands = new ArrayList<>();
    private BlacklistService stopFactorService;

    public LoanApplicationService() {
        // Добавляем команды проверки стоп-факторов в очередь
        stopFactorCommands.add(new BlacklistCheckCommand("client123", new StopFactorService()));
        // Здесь могут быть другие команды, такие как проверка на задолженность и т.д.
    }


    public void processLoanApplication() {
        // Прямые вызовы проверок, усложняются при добавлении новых
        if (stopFactorService.isBlacklisted("client123")) {
            System.out.println("Отказ в кредитовании: чёрный список");
        }
        if (stopFactorService.hasOutstandingDebt("client123")) {
            System.out.println("Отказ в кредитовании: задолженность");
        }
        // и так далее...
    }

}

