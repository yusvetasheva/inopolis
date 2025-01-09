package com.example;

//1. Создать класс расширяющий Thread
//Создать класс NewThread расширяющий класс Thread.
//Переопределить метод run(). В цикле for вывести на консоль символ 100 раз.
//Создать экземпляр класса и запустить новый поток.
public class NewThread extends Thread{
    @Override
    public void run(){
        for (int i =0; i<100; i++)
            System.out.println("123");
    }
}
