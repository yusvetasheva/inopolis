package com.example.structures;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Кэш с ограниченным размером, который удаляет наименее используемые элементы.
 */
public class LRUKashList<T> {
    @Getter
    private final Integer maxSize;
    LinkedList<T> objList = new LinkedList<>();

    public LRUKashList(int maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getCurrentSize() {
        return objList.size();
    }

    public synchronized void addObject(T obj) {

        if (objList.size() >= maxSize) {
            objList.removeFirst();
        }

        objList.add(obj);
    }

    public T getObjByIndex(int index) {

        if (index < 0 || index > objList.size() - 1) throw new IndexOutOfBoundsException();

        return objList.get(index);
    }
}
