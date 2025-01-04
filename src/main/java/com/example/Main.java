package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        //Задан массив целых чисел от 1 до 100.
        // В этом массиве есть дубликаты. Задача: найти все дубликаты и указать их количество.
        // Например 1, 2, 3, 4, 4, 5, 6, 7, 7, 7 4 — 2 штуки, 7 — 3 штуки.

        int[] array = new int[]{1, 1, 2, 3, 4, 4, 5, 6, 6, 7, 8, 8, 8, 8};

        //key - num, value - iter
        Map<Integer, Integer> map = Arrays.stream(array)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                ));

        map.forEach((k,v)->System.out.println(k + " = " + v));

        map.forEach((k,v)-> {
            if (v>1){
                System.out.println(k + " - " + v + " штуки");
            }
        });

        Map <Integer, Integer> other = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(
                        i->i,
                        Collectors.summingInt(i->1)
                ));



    }

    //Сделать первую букву каждого слова заглавной
    public static void getBigName(String input) {
        String[] array = input.split(" ");
        String result = Arrays.stream(array)
                .map(i -> i.substring(0, 1).toUpperCase() + i.substring(1))
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    //Задача 2. Напишите функцию, которая принимает на вход массив чисел
    // и возвращает новый массив c тем же количеством элементов,
    // где каждый элемент является произведением всех элементов исходного массива, кроме текущего.
    public static void getProductArrayStreams(double[] numsArray) {
        double total = Arrays.stream(numsArray).reduce((x, y) -> x * y).getAsDouble();
        double[] result = Arrays.stream(numsArray).map(i -> total / i).toArray();
        System.out.println(Arrays.toString(result));
    }


    //Задача 2. Напишите функцию, которая принимает на вход массив чисел
    // и возвращает новый массив c тем же количеством элементов,
    // где каждый элемент является произведением всех элементов исходного массива, кроме текущего.
    public static double[] getClassName(double[] numsArray) {
        double all = 1;
        for (int i = 0; i < numsArray.length; i++) {
            all = numsArray[i] * all;
        }

        double[] result = new double[numsArray.length];

        for (int i = 0; i < numsArray.length; i++) {
            result[i] = all / numsArray[i];
        }

        return result;
    }

}
