package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApprovalService {

    private final CreditHistoryCheck creditHistoryCheck;
    private final ClientVerificationCheck clientVerificationCheck;

    @Autowired
    public LoanApprovalService(CreditHistoryCheck creditHistoryCheck, ClientVerificationCheck clientVerificationCheck) {
        this.creditHistoryCheck = creditHistoryCheck;
        this.clientVerificationCheck = clientVerificationCheck;
    }

    public boolean approveLoan(Client client) {
        if (!creditHistoryCheck.check(client)) {
            return false;
        }
        if (!clientVerificationCheck.check(client)) {
            return false;
        }
        // Логика одобрения кредита
        return true;
    }
}


