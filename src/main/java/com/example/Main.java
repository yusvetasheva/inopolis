package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /**Задание 1*/

        // Из массива в список
        Integer[] intArray = {1, 2, 6, 8, 3, 5, 9, 0, 0};
        List<Integer> intList = Arrays.asList(intArray);
        System.out.println("Из массива в список");
        intList.forEach(System.out::println);

        //Прямая сортировка
        Collections.sort(intList);
        System.out.println("Прямая сортировка");
        intList.forEach(System.out::println);

        //Обратная сортировка
        intList.sort(Collections.reverseOrder());
        System.out.println("Обратная сортировка");
        intList.forEach(System.out::println);

        //Перемешиваем
        Collections.shuffle(intList);
        System.out.println("Перемешиваем");
        intList.forEach(System.out::println);

        //Циклический сдвиг на 1
        Collections.rotate(intList, 1);
        System.out.println("Циклический сдвиг на 1");
        intList.forEach(System.out::println);

        //Только уникальные элементы
        Set<Integer> setInt = new HashSet<>(intList);
        System.out.println("Только уникальные элементы");
        setInt.forEach(System.out::println);

        //только дублирующиеся объекты
        //key - само число, значение - количество повторений
        Map<Integer, Integer> mapForDublicates = new HashMap<>();
        intList.forEach(i -> {
            if (mapForDublicates.get(i) == null) mapForDublicates.put(i, 1);
            else {
                Integer count = mapForDublicates.get(i) + 1;
                mapForDublicates.put(i, count);
            }
        });

        List<Integer> result = new ArrayList<>();
        for (Integer key : mapForDublicates.keySet()) {
            if (mapForDublicates.get(key) > 1)
                result.add(key);
        }
        System.out.println("Только дублирующиеся элементы");
        result.forEach(System.out::println);

        //Из массива список
        List<Integer> listFromArray = List.of(intArray);
        System.out.println("Из массива список");
        listFromArray.forEach(System.out::println);

        /**Задание 2*/
        //добавили 10 элементов
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("10");
        hashSet.add("11");
        hashSet.add("12");
        hashSet.add("13");
        hashSet.add("14");
        hashSet.add("15");
        hashSet.add("16");
        hashSet.add("17");
        hashSet.add("18");

        System.out.println("Создали и заполнили HashSet");
        hashSet.forEach(System.out::println);


        //Добавили пять объектов, совместимые со String
        hashSet.add(String.valueOf(199));
        hashSet.add(LocalDate.now().toString());
        hashSet.add(BigDecimal.TEN.toString());
        hashSet.add(String.valueOf('8'));
        hashSet.add(String.valueOf(19f));

        System.out.println("Добавили пять объектов, совместимые со String");
        hashSet.forEach(System.out::println);

        //Выводим через for
        System.out.println("Выводим все через for");
        for (String i : hashSet){
            System.out.println(i);
        }

        // Добавили уже существующий элемент
        // Он не добавился
        hashSet.add("16");
        System.out.println("Добавили элемент, который уже есть");
        hashSet.forEach(System.out::println);

        //Удалям из коллекции элемент
        hashSet.remove("1");
        System.out.println("Удалили единицу");
        hashSet.forEach(System.out::println);

        //Количество элементов
        System.out.println("Количество элементов: " + hashSet.size());

        //Удаляем все элементы
        hashSet.clear();
        System.out.println("Удаляем все элементы: " + hashSet.size());

        //Проверяем является ли hashSet пустым
        System.out.println("Коллекция пустая? - " + hashSet.isEmpty());




    }

}
