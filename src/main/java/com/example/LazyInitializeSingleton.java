package com.example;

public class LazyInitializeSingleton {
    private LazyInitializeSingleton() {
    }

    private static LazyInitializeSingleton instanse;

    public static synchronized LazyInitializeSingleton getInstance() {
        if (instanse == null) instanse = new LazyInitializeSingleton();
        return instanse;
    }

}
