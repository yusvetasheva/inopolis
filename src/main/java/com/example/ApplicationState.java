package com.example;

import org.springframework.context.ApplicationContext;

public interface ApplicationState {
    void handle(ApplicationContext context);
}
