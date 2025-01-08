package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        change();
    }

    // Напишите программу на Java для того, чтобы поменять местами значения,
    // хранящиеся в двух переменных с помощью третьей переменной
    public static void change(){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int temp;
        //меняем
        temp = a;
        a = b;
        b=temp;
        System.out.println("a = " + String.valueOf(a) + ", b = " + String.valueOf(b));
    }

    //Напишите программу на Java для переворота последовательности символов в строке
    // без использования встроенной в String функции reverse()
    public static void myReverce1(String input){
        String[] array = input.split("");
        Deque<String> deq = new ArrayDeque<>();
        for (String i: array){
            deq.push(i);
        }
        String result = String.join("", deq);
        System.out.println(result);

    }

    //Напишите программу на Java для переворачивания строки,
    // изменив расположение символов в строке задом наперёд без использования встроенных в String функций
    public static void myReverve(String input){
        StringBuilder str = new StringBuilder(input);
        System.out.println(str.reverse());
    }

    //Из массива целых чисел, надо найти подмассив с наибольшей суммой и вернуть её сумму.
    //       Например:
    // Ввод: [-2,1,-3,4,-1,2,1,-5,4]
    // Подмассив [4,-1,2,1] имеет наибольшую сумму 6.
    // Вывод: 6
    public static Integer getSum(Integer [] input){
        if (input==null || input.length==0) return 0;

        Integer max = 0;
        Integer curr = 0;

        for (int i=0; i<input.length; i++){
            curr+=input[i];
            if (curr>max){
                max = curr;
            }
            else if (curr<0) curr = 0;
        }
        return  max;
    }


    //Дан массив целых чисел, отсортированный по возрастанию,
    // верните массив квадратов каждого числа, отсортированный по возрастанию.
    //Ввод: [-7,-3,2,3,11]
    //Вывод: [4,9,9,49,121]
    public static Integer[] getSquare(Integer[] input) {
        Integer[] result = Arrays.stream(input).map(i -> Math.abs(i)).sorted().map(i -> i * i).toArray(Integer[]::new);
        Arrays.stream(result).forEach(System.out::println);
        return result;
    }

    //Есть строка «Привет, Кккккатовццццы!»,
    // надо избавиться от дубликатов и вывести «Привет, Катовцы!».
    public static StringBuilder deleteDublicate(String input) {
        StringBuilder str = new StringBuilder();

        char[] sighs = input.toCharArray();
        char prev = sighs[0];
        str.append(prev);

        for (int i = 1; i < sighs.length; i++) {
            if (Character.toLowerCase(prev) != Character.toLowerCase(sighs[i])) {
                str.append(sighs[i]);
                prev = sighs[i];
            }
        }

        return str;
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
