package com.example.structures;

import java.util.ArrayDeque;

/**
 * Очередь фиксированного размера с операциями добавления и удаления.
 * */
public class BoundedQueue<T> {
    private final int maxSize;
    private ArrayDeque<T> queue;

    BoundedQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new ArrayDeque<T>(maxSize);
    }

    public synchronized void add (T obj){
        if (queue.size()==maxSize){
            queue.pollFirst();
            /**
             * removeFirst - выбросит исключение, если очередь пуста,
             * а pollFirst в этом случае вернет null
             * */
        }

        queue.offer(obj);
    }
}
