package com.example.structures;

import java.util.concurrent.atomic.AtomicBoolean;

public class Mutex {

    private AtomicBoolean busy = new AtomicBoolean(false);
    private Thread owner;

    public void lock(){
       while (!busy.compareAndSet(false, true)){
           Thread.yield();
       }

       owner = Thread.currentThread();
    }

    public void unlock(){
        if (Thread.currentThread().equals(owner)){
            owner = null;
            busy.set(false);
        }
        else{
            Thread.yield();
        }
    }

}
