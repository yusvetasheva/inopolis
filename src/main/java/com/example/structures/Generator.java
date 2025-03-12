package com.example.structures;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Генерирует уникальные ID, например, на основе времени или случайных чисел.
 */
public class Generator {
    private final AtomicInteger id;
    private final Random RANDOM = new Random();

    public Integer getId() {
        return this.id.get();
    }

    public Generator(LocalDateTime dateTime) {
        this.id = new AtomicInteger( dateTime.getYear() * RANDOM.nextInt(3, 9)
                + dateTime.getMonthValue() * RANDOM.nextInt(11, 15)
                + dateTime.getDayOfMonth() * RANDOM.nextInt(16, 19)
                + dateTime.getHour() * RANDOM.nextInt(0, 2)
                + dateTime.getMinute() * RANDOM.nextInt(4, 3)
                + dateTime.getSecond() * RANDOM.nextInt(8, 13));
    }

}
