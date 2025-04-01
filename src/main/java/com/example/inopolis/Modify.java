package com.example.inopolis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Modify {

    private volatile static int counter_1 = 0;

    private volatile static int counter_2 = 0;

    public static void main(String[] args) throws InterruptedException {
        int taskCount = 10000;
        CountDownLatch latch = new CountDownLatch(taskCount);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < taskCount; i++) {
            executorService.submit(() -> {
                counter_1++;
                counter_2++;
                latch.countDown();
            });
        }
        latch.await();
        System.out.println(counter_1);
        System.out.println(counter_2);
        System.exit(0);
    }

}
