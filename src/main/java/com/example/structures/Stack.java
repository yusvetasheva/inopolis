package com.example.structures;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T obj){
        list.addFirst(obj);
    }

    public T pop (){
        T result = list.getFirst();
        list.removeFirst();
        return result;
    }

    public T peek(){
        return list.getFirst();
    }
}
