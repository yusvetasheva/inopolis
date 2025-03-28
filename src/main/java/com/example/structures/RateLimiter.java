package com.example.structures;

import java.util.*;

public class RateLimiter {
    private final Integer maxRequestCount;
    private final Long timeWindow;

    // надо хранить текущее время сессии для каждого пользователя
    Map<String, Long> currentWindow = new HashMap<>();

    // Тут храним временные метки каждого запроса
    Map<String, ArrayDeque<Long>> requestTime = new HashMap<>();

    public RateLimiter(Integer maxRequestCount, Long timeWindow) {
        this.maxRequestCount = maxRequestCount;
        this.timeWindow = timeWindow;
    }

    public boolean isAllowed(String userId, Long timeStamp) {

        //первый запрос от пользователя в этом окне
        if (currentWindow.get(userId) == null) {
            currentWindow.put(userId, 0L);
        }

        //Если старое окно устарело
        // очищаем очередь запросов
        //ставим новое окно
        else if (currentWindow.get(userId) >= timeWindow) {
            requestTime.get(userId).remove();
            currentWindow.put(userId, 0L);
        }

        //Отказываем в запросе если
        // 1. Превысили кол-во запросов
        if (requestTime.get(userId).size() >= maxRequestCount) return false;

        requestTime.putIfAbsent(userId, (new ArrayDeque<>()));
        requestTime.get(userId).addLast(timeStamp);

        currentWindow.put(userId, currentWindow.get(userId) + timeStamp);

        return true;
    }
}
