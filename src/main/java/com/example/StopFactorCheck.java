package com.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public interface StopFactorCheck {
    boolean check(String clientId);
}



