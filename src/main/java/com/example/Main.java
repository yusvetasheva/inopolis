package com.example;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    @SneakyThrows
    public static void main(String[] args) {

        //Есть 4 вида ссылок. Тут они перечислены от самой сильной к самой слабой
        /**Strong
         * Не удаляется gc*/
        Object a = new Object();
        System.out.println("strong reference " + a);

        /**Soft
         * Удаляется gc при нехватке памяти*/
        SoftReference<Object> soft = new SoftReference<>(a);
        System.out.println("soft reference " + soft.get());

        /**Weak
         * Удаляется при 1ом запуске gc*/
        WeakReference<Object> weak = new WeakReference<>(a);
        System.out.println("weak reference " + weak.get());

        /**Fantom
         * Всегда возвращает null при get*/
        ReferenceQueue <Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(a, referenceQueue);
        System.out.println("phantom reference " + phantomReference.get());

        //Если делаем переменную null, то soft и weak сохраняют ссылку на область памяти
        //У фантомной ссылки ссылка на объект сохранится в очереди ссылок (но у меня почему-то null)
        //strong занулиться
        System.out.println("После a = null");
        a = null;
        System.out.println("strong reference " + a);
        System.out.println("soft reference " + soft.get());
        System.out.println("weak reference " + weak.get());
        System.out.println("phantom reference " + referenceQueue.poll());

        //Вызовем gc и убедимся, что он удалит слабую ссылку (мы сделаем попытку вызова, но вызов не гарантирован)
        System.gc();
        System.out.println("После вызова GC");
        System.out.println("weak reference " + weak.get());


    }

}
