package com.example;


public class SingletonClass {
    private static final SingletonClass instance = new SingletonClass();

    /**Конструктор private*/
    private SingletonClass() {
    }

    public static SingletonClass getInstance(){
        return instance;
    }
}
