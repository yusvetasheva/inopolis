package com.example;

//        2. Создать класс реализующий Runnable
//        Создать класс, реализующий интерфейс Runnable.
//        Переопределить run() метод. Создать цикл for. В цикле распечатываем значения от 0 до 100 делящиеся на 10 без остатка.
//        Используем статический метод Thread.sleep(), чтобы сделать паузу.
//        Создать три потока, выполняющих задачу распечатки значений.
public class NewRunnable implements Runnable {
    @Override
    public synchronized  void run() {
        for (int i =0; i<100; i++){
            if (i%10==0)
                System.out.println(i);
        }
    }
}
