package com.example.structures;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Напишите класс, который реализует счетчик с функциями увеличения, уменьшения и сброса значения.
 */

public class Schetchik {
    private AtomicInteger counter;

    public Schetchik(){
        this.counter = new AtomicInteger(0);
    }

    public Schetchik(int value){
        this.counter = new AtomicInteger(value);
    }

    public void uvelich() {
        this.counter.incrementAndGet();
    }

    public void umensh() {
        this.counter.decrementAndGet();
    }

    public void sbros() {
        this.counter.set(0);
    }

    public Integer getValue(){
        return this.counter.get();
    }
}
