package com.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        findMaxSubarray();

    }

    /**
     * Условие задачи:
     * дан связный список (linked list), поменять порядок элементов на противоположный.
     *
     * Примеры:
     * Вход: 1->2->3->4->5, Выход: 5->4->3->2->1
     * Вход: 1, Выход: 1
     * */


    public static LinkedList<Integer> reverseList(){
        LinkedList <Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        LinkedList <Integer> temp = new LinkedList<>();

        for (Integer i : list){
            temp.addFirst(i);
        }

        return temp;

    }

    /**
     * Есть мультимапа и ее нужно развернуть
     * */



    /**
     * Задан отрезок. Нужно найти максимальную длину хорошего подотрезка
     * Хороший подотрезок = последовательность, в которой не больше k различных элементов
     */

    public static int findGood() {
        int[] nums = {1, 2, 23, 4, 3, 2, 2, 4, 5, 65};
        int k = 3;

        int currUnic = 0;
        int maxLen = 0;

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        int left = 0;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            //Если элемент уникален, и текущее количество уникальных элементов позволяет, то расширяем список
            if (!list.contains(nums[i]) && currUnic < k) {
                list.add(nums[i]);
                currUnic++;
            }
            else if (list.contains(nums[i])) {
                list.add(nums[i]);
                if (list.get(left)==nums[i])
                    right = i;
            }
            //Если элемент уникален, и текущее количество уникальных элементов переполнено
            // двигаем левую границу пока не избавимся от одного элемента в списке (от всех его вхождений)
            else if (!list.contains(nums[i]) && currUnic >= k) {
                list.subList(left, right).clear();
                set.addAll(list);
                currUnic = set.size();
                left = right+1;
                right = left;
            }

            if (list.size()>maxLen)
                maxLen = list.size();
        }
        return maxLen;

    }

    /**
     * Дан массив целых чисел
     * нужно найти подмассив с наибольшей суммой элементов
     * вывести левую границу, правую границу и сумму
     */
    public static int[] findMaxSubarray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int left = 0;
        int right = 0;
        int max = Integer.MIN_VALUE;
        int curr = 0;

        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];

            if (curr > max) {
                max = curr;
                right = i;
            } else if (curr < 0) {
                curr = 0;
                left = i + 1;
                right = i + 1;
            }
        }

        int[] result = {left, right, max};
        return result;
    }

    /**
     * На вход массив банок, которые нужно слить в бидоны (банку можно слить только целиком)
     * и количество бидонов неизвестного объема
     * Какой должен быть минимальный объем каждого бидона, чтобы все банки можно ыбл в них слить
     */
    public static int getVolume() {
        int[] banki = {1, 8, 7, 5, 6, 7, 8, 9, 3, 4};
        int count = 4;
        int max = Integer.MIN_VALUE;

        int sum = 0;
        int result = 0;

        for (int i : banki) {
            sum += i;
            if (max < i) max = i;
        }

        //Тут важно учитывать, что банку нужно слить целиком и нужно, чтобы самая  большая банка уместилась
        result = sum / count;
        if (sum % count > 0) result++;


        return Math.max(result, max);

    }


    /**
     * на вход 2 элемента
     * масиив банок, которые нужно слить в бидон (банка всегда выливается целиком в бидон)
     * и объем бидона
     * Сколько минимально нужно бидонов, чтобы слить в них все банки
     */

    public static int banka() {
        int[] banki = {1, 9, 8, 5, 10, 4, 7};
        int bedon = 12;

        int result = 0;
        int curr = 0;

        for (int i : banki) {
            if (curr + i > bedon) {
                result++;
                curr = i;
            } else curr += i;
        }

        if (curr > 0) result++;

        return result;
    }

    /**
     * Дана последовательность неотрицательных целых чисел
     * Нужно переставить нули в конец, не меняя порядок других чисел
     * [1,0,2,3.0,0]->[1,2,3,0,0,0]
     */

    public static int[] reverseArray() {
        int[] nums = {1, 0, 2, 0, 4, 5, 6};
        int[] result = new int[nums.length];

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result[index] = nums[i];
                index++;
            }
        }

        return result;
    }

    public static boolean isHappy(int n) {

        Set<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = digitSqr(n);
        }
        return n == 1;
    }

    public static int digitSqr(int digit) {
        int result = 0;
        while (digit > 0) {
            result += digit % 10 * digit % 10;
            digit /= 10;
        }
        return result;
    }

    //Дан массив строк. Напишите функцию,
    // которая находит первую повторяющуюся букву.
    public static String check4() {
        String[] array = {"a", "b", "a"};
        Set<String> set = new HashSet<>();

        for (String i : array) {
            if (!set.add(i)) {
                return i;
            }
        }

        return "";
    }

    //Дан массив строк. Напишите функцию,
    // которая находит слово с максимальной частотой появления.
    public static void check3() {
        String[] array = {"q", "s", "x", "q"};

        Map<String, Integer> map = Arrays.stream(array).collect(
                Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                )
        );

        int max = -1;
        String result = "";

        for (Map.Entry<String, Integer> i : map.entrySet()) {
            if (i.getValue() > max) {
                max = i.getValue();
                result = i.getKey();
            }
        }

        System.out.println(result);

    }

    //Напишите функцию, которая проверяет, содержит ли строка только уникальные символы.
    public static boolean check2() {
        String str = "1234565";

        Set<Character> set = new HashSet<>();

        for (char c : str.toCharArray()) {
            if (!set.add(c))
                return false;
        }

        return true;
    }

    //Сумма подмассива
    //Дан массив целых чисел. Найдите максимальную сумму непрерывного подмассива.
    public static void max2() {
        int[] array = {1, 2, 3, 4, 5, 6, -1, 10, -90, 999, 22};

        int currSum = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            currSum += array[i];

            if (currSum > max)
                max = currSum;

            if (currSum < 0)
                currSum = 0;

        }
    }

    //Описание: Дан массив символов.
    // Напишите функцию, которая проверяет, является ли одна строка перестановкой другой.
    public static void perest() {

        String str1 = "abbc";
        String str2 = "bacb";

        if (str1.length() != str2.length()) System.out.println("Нет по длине");

        int[] array = new int[26];

        for (char c : str1.toCharArray()) {
            array[c - 'a']++;
        }

        for (char a : str2.toCharArray()) {
            array[a - 'a']--;
        }

        for (int i : array) {
            if (array[i] != 0) {
                System.out.println("Нет по составу");
                break;
            }
        }

        System.out.println("Даааа");
    }

    /**
     * у hashSet нет операции get
     * Можно использовать итератор для перебора элементов и получения нового
     */
    public static void hashSet() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("w");


    }

    /**Задача: Напишите метод, который переворачивает слова в строке, сохраняя их порядок.*/

    /**
     * Итеративный факториал
     */
    public static void countFactorial() {
        int input = 5;
        int result = 1;

        while (input > 1) {
            result *= input;
            input--;
        }
        System.out.println(result);
    }

    /**
     * Рекурсивный факториал
     */

    public static long factorialRecursive(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursive(n - 1);
    }

    /**
     * Напишите метод, который возвращает первый неповторяющийся символ в строке.
     * оказыкается метод forEach StreamAPI нельзя прервать
     */
    public static void detUnic() {
        String input = "11223335667";
        String[] array = input.split("");

        Map<String, Integer> map = Arrays.stream(array).collect(Collectors.toMap(
                i -> i,
                i -> 1,
                Integer::sum

        ));

        for (Map.Entry<String, Integer> i : map.entrySet()) {
            if (i.getValue() == 1) {
                System.out.println(i.getKey());
                break;
            }
        }
    }


    /**
     * Найдите самую длинную подстроку без повторяющихся символов
     */
    public static void findMaxStr() {
        String input = "ja71 hssys g = + jsdgug ufdygvjsbcv";
        Set<String> result = Stream.of(input.split("")).collect(Collectors.toSet());
        String str = String.join("", result);
        System.out.println(str);
    }

    /**
     * Проверить 2 строки на анаграмму (набор символов и их количество одинаковое, но порядок разный)
     */
    public static void isAnagramma() {
        String first = "abcaf";
        String second = "cbaav";

        String str1 = Arrays.stream(first.split("")).sorted().collect(Collectors.joining());
        String str2 = Arrays.stream(second.split("")).sorted().collect(Collectors.joining());

        System.out.println(str1.equals(str2));
    }

    public static void find2max() {
        int[] array = {1, 6, 4, 5, 7, 8, 9, 66, 99};

        List<Integer> result = Arrays.stream(array).boxed().sorted(Comparator.reverseOrder()).toList();
        System.out.println(result.get(1));

        //Вариант без StreamApi
        int[] arr = {1, 6, 4, 5, 7, 8, 9, 76, 100};
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (max2 < arr[i]) {
                max2 = arr[i];
            }
        }

        System.out.println(max2);
    }

    //Напишите метод, который сортирует массив целых чисел
    public static void arraySolt() {
        int[] a = {1, 2, 3, 4, 6, 3, 4, 6, 22, 5, 7, 7, 82, 92};

        //По убыванию
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        Arrays.stream(a).forEach(System.out::println);
    }

    /**
     * 1) StringBuilder не принимает в конструктор int, но принимает String
     * 2) У String нет reverse, нужен StringBuilder
     * 3) Integer.parseInt() не принимает на вход StringBuilder, только стринг
     * <p>
     * *
     */
    public static void reverseInt() {
        int input = -1230;
        String str = String.valueOf(input);
        boolean flag = false;
        if (str.contains("-")) {
            flag = true;
            str = str.replace("-", "");
        }

        int result = Integer.parseInt(String.valueOf((new StringBuilder(str)).reverse()));
        if (flag) result *= -1;


        System.out.println(result);
    }

    public static void reverseInt1() {
        int input = -12367;
        long result = 0L;

        while (input != 0) {
            int sign = input % 10;
            result = result * 10 + sign;
            input = input / 10;

            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
                result = 0;
        }

        System.out.println(result);


    }

    //написать код, который выводит числа от 0 до 1000, которые делятся на 3, но не делятся на 5,
    // и сумма цифр в которых меньше десяти.
    public static void delenie() {
        for (int i = 0; i < 1001; i++) {
            Integer sum = Stream.of(String.valueOf(i).split(""))
                    .map(Integer::parseInt)
                    .reduce(Integer::sum).orElse(0);
            if (i % 3 == 0 && i % 5 != 0 && sum.compareTo(10) < 1)
                System.out.println(i);
        }
    }

    // для удаления всех пробелов из строки без использования replace().
    public static void deleteSpace() {
        String input = "ysgs s hs  sgggf a f";
        List<String> list = List.of(input.split(""));
        List<String> result = list.stream().filter(i -> !i.equals(" ")).toList();
        result.forEach(System.out::print);
    }

    //для удаления всех пробелов из строки с помощью replace()
    public static void removeSpace() {
        String input = "8 h h yysh uhsihd. jsh";
        String result = input.replaceAll(" ", "");
        System.out.println(result);
    }

    //для проверки является ли введенное число - числом Армстронга
    public static void isArmstrong() {
        System.out.println("Введите число");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] signs = input.split("");
        int size = signs.length;
        int result = 0;

        for (String i : signs) {
            int sign = Integer.parseInt(i);
            result += Math.pow(sign, size);
        }

        int intInput = Integer.parseInt(input);
        System.out.println((intInput == result) ? "Число Армстронга" : "Нет");
    }

    //Найти все дубликаты в массиве
    public static void findD() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3};
        Map<Integer, Integer> map = Arrays.stream(a).boxed().collect(
                Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                ));
    }

    //Напишите Java-программу, чтобы найти второе по величине число в массиве.
    public static void findTwoMax() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 6, 7, 4, 5, 9, 12, 13, 30};
        List<Integer> result = Stream.of(array).sorted(Comparator.naturalOrder()).toList();
        System.out.println(result.get(result.size() - 2));

    }

    //Напишите программу на Java, чтобы найти повторяющиеся символы в строке
    //вывод a-2 раза, b- 3 раза
    public static void findRepeat() {
        String input = "asdfsdasdghj";
        Map<String, Integer> result = Stream.of(input.split("")).collect(
                Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                )
        );

        result.forEach((k, v) -> {
            if (v > 1) {
                System.out.println(k + " встретилось " + v + " раз");
            }
        });


    }

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
