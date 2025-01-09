package com.example;

import lombok.SneakyThrows;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        //Через Thread
        NewThread newThread = new NewThread();
        newThread.start();

        //Через Runnable
        NewRunnable newRunnable = new NewRunnable();
        Thread thread1 = new Thread(newRunnable,"thread-1");
        Thread thread2 = new Thread(newRunnable, "thread-2");
        Thread thread3 = new Thread(newRunnable,"thread-3");

        thread1.start();
        thread2.start();
        thread3.start();


    }

}
