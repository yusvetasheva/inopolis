package com.example.structures;

public class MultyIterator<T> {

    private final MultyIterator<T> a;
    private final MultyIterator<T> b;

    public MultyIterator(MultyIterator<T> a, MultyIterator<T> b) {
        this.a = a;
        this.b = b;
    }

    public boolean hasNext(){
        return a.hasNext() || b.hasNext();
    }

    public T next(){
        if (a.hasNext()) return a.next();
        return b.next();
    }
    
}
