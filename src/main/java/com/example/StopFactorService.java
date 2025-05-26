package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StopFactorService {
    private final ApplicationEventPublisher publisher;

    public StopFactorService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void checkStopFactors(String clientId) {
        // Проверка логики
        boolean result = ...;

        // Публикация события
        publisher.publishEvent(new StopFactorCheckEvent(this, clientId, result));
    }
}









