package com.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        isRepeat();
    }

    //Напишите Java-программу, чтобы найти второе по величине число в массиве.

    //Date Time API
    public static void modifyDate() {

        countWorlds();

        //Как добавить 1 неделю, 1 месяц, 1 год, 10 лет к текущей дате с использованием Date Time API?
        LocalDate now = LocalDate.now();
        now = now.plusDays(1);
        System.out.println(now);

        //Как получить следующий вторник используя Date Time API?
        System.out.println(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
    }

    //Напишите программу на Java, чтобы найти повторяющиеся символы в строке.
    public static void isRepeat() {
        String input = "1234456";


        Set<String> set = new HashSet<>(List.of(input.split((""))));
        boolean result = false;

        System.out.println(input.length() == set.size() ? "Нет дубликатов" : "Есть дубликатыы");
    }

    //Написать программу на Java для вычисления серии чисел Фибоначчи.
    public static void isFibonachi() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> list = List.of(input.split(" "));
        List<Integer> intList = list.stream().map(Integer::valueOf).toList();
        boolean result = true;

        Integer first = intList.get(0);
        Integer second = intList.get(1);

        for (int i = 2; i < intList.size(); i++) {
            Integer next = first + second;
            if (next.compareTo(intList.get(i)) != 0) {
                result = false;
                break;
            }
            first = second;
            second = intList.get(i);
        }

        System.out.println((result) ? "Да" : "Нет");

    }


    // Напишите программу для подсчета количества конкретных слов в строке, используя HashMap.
    public static void countWorlds() {
        String input = "a v b n b v c v n";

        Map<String, Integer> map = Stream.of(input.split(" "))
                .collect(Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                ));

        map.forEach((k, v) -> System.out.println(k + " = " + v));

        //Напишите программу для итерации объекта типа HashMap
        // с использованием цикла while и улучшенного цикла for.

        //Это через цикл for
        for (Map.Entry enMap : map.entrySet()) {
            System.out.println(enMap.getKey() + " == " + enMap.getValue());
        }

        //Это через while
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry item = (Map.Entry) iterator.next();
            System.out.println(item.getKey() + "===" + item.getValue());
        }
    }

    //Напишите Java-программу, чтобы определить, является ли строка или число палиндромом, или нет.
    public static void isPalindrom() {
        String input = "шалаr";
        String[] array = input.split("");
        boolean result = true;
        Deque<String> deq = new ArrayDeque<>();
        if (array.length % 2 == 0) result = false;
        else {
            for (int i = 0; i < array.length / 2; i++) {
                deq.push(array[i]);
            }
            for (int i = array.length / 2 + 1; i < input.length(); i++) {
                if (!deq.pop().equals(array[i])) {
                    result = false;
                    break;
                }
            }
        }

        System.out.println((result) ? "Палиндром" : "Нет");
    }

    //Напишите программу на Java, чтобы узнать, является ли число простым или нет.
    public static void isSimple() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        boolean flag = true;

        for (int i = 2; i <= input / 2; i++) {
            if (input % i == 0) {
                flag = false;
                break;
            }
        }
        String result = String.valueOf(input) + " является ";
        result += (flag) ? "простым" : "сложным";
        System.out.println(result);
    }

    //Как получить среднее значение всех чисел?
    public static Double getMeanValue() {
        return Stream.of(1, 2, 3, 4, 5).mapToInt(i -> i).average().getAsDouble();
    }

    //Как получить сумму всех чисел в наборе?
    public static Integer getSum() {
        return Stream.of(1, 6, 7, 4, 5, 6).reduce(Integer::sum).get();
    }

    //Как найти максимальное число в наборе?
    public static Integer findMax() {
        return Stream.of(1, 8, 7, 56, 789, -9, 54)
                .mapToInt(i -> i)
                .max().
                getAsInt();
    }

    //Как найти минимальное число в наборе?
    public static Integer findMin() {
        return Stream.of(1, 8, 9, 23, -10, -3838, 1, 7)
                .min(Comparator.naturalOrder())
                .get();
    }

    //Как вывести на экран 10 случайных чисел, используя forEach()?
    public static void getRandom() {
        (new Random())
                .ints()
                .filter(i -> i > 0)
                .limit(10)
                .forEach(System.out::println);
    }

    //Как можно вывести на экран уникальные квадраты чисел используя метод map()
    public static void getUniqSquare() {
        (Stream.of(-1, 1, 2, 3, 4, 5))
                .map(i -> i * i)
                .distinct()
                .forEach(System.out::println);
    }

    //Как вывести на экран 10 случайных чисел в порядке возрастания
    public static void getTenNumbers() {
        (new Random()).ints().limit(10).sorted().forEach(System.out::println);
    }

    //Как вывести на экран количество пустых строк с помощью метода filter()?
    @SneakyThrows
    public static void getEmptyLines() {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("json.txt"));
        String line = "";
        List<String> list = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }

        long emptySize = list.stream().filter(i -> i.toString().trim().isEmpty()).count();
        System.out.println("Пустых строк: " + emptySize);
    }

    // Напишите программу на Java для того, чтобы поменять местами значения,
// хранящиеся в двух переменных с помощью третьей переменной
    public static void change() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int temp;
        //меняем
        temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + String.valueOf(a) + ", b = " + String.valueOf(b));
    }

    //Напишите программу на Java для переворота последовательности символов в строке
// без использования встроенной в String функции reverse()
    public static void myReverce1(String input) {
        String[] array = input.split("");
        Deque<String> deq = new ArrayDeque<>();
        for (String i : array) {
            deq.push(i);
        }
        String result = String.join("", deq);
        System.out.println(result);

    }

    //Напишите программу на Java для переворачивания строки,
// изменив расположение символов в строке задом наперёд без использования встроенных в String функций
    public static void myReverve(String input) {
        StringBuilder str = new StringBuilder(input);
        System.out.println(str.reverse());
    }

    //Из массива целых чисел, надо найти подмассив с наибольшей суммой и вернуть её сумму.
//       Например:
// Ввод: [-2,1,-3,4,-1,2,1,-5,4]
// Подмассив [4,-1,2,1] имеет наибольшую сумму 6.
// Вывод: 6
    public static Integer getSum(Integer[] input) {
        if (input == null || input.length == 0) return 0;

        Integer max = 0;
        Integer curr = 0;

        for (int i = 0; i < input.length; i++) {
            curr += input[i];
            if (curr > max) {
                max = curr;
            } else if (curr < 0) curr = 0;
        }
        return max;
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
