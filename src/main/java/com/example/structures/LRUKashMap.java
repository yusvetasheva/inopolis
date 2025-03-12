package com.example.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUKashMap<K,V> {

    private final int capacity;

    LinkedHashMap<K,V> kash;

    public LRUKashMap(int capacity) {
        this.capacity = capacity;
        this.kash = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity; // Удаляет первый (старый) элемент, если размер превышен
            }
        };
    }

    public void addObj(K key, V value){
        kash.put(key, value);
    }

    public V get(K key){
        return kash.getOrDefault(key, null);
    }
}
