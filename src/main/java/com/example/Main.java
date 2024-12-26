package com.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        //Создаем HashMap. Читаем из файла
        Map<String, String> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("source.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(", ");
                for (String i : array) {
                    String result = i.replaceAll("[{}\"]", "");
                    String[] mapValue = result.split(":");
                    map.put(mapValue[0].trim(), mapValue[1].trim());
                }
            }
            map.forEach((k, v) -> System.out.println(k + "=" + v));
        }
        System.out.println("=============== a =================");

        //Добавляем 5 значений
        map.put("new", "111");
        map.put("orange", "166");
        map.put("z", "188");
        map.put("c", "199");
        map.put("u", "200");

        map.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println("=============== b =================");

        //прямой перебор
        for (String key : map.keySet())
            System.out.println(key + " = " + map.get(key));
        System.out.println("=============== c =================");

        //Добавляем элемент с повторяющимся ключом
        map.put("z", "0");
        System.out.println(map.get("z"));
        System.out.println("=============== d =================");

        //Выносим список ключей в отдельную коллекцию
        List<String> keyList = map.keySet().stream().toList();
        keyList.forEach(System.out::println);
        System.out.println("=============== e =================");

        /**Вынести список всех значений из HashMap в коллекцию HashSet
         * и получить количество уникальных значений.*/
        HashSet<String> hashSet = new HashSet<>(map.keySet());
        hashSet.forEach(System.out::println);
        System.out.println(hashSet.size());
        System.out.println("=============== f =================");

        /**Ищем ключ*/
        System.out.println(map.containsKey("yolo"));
        System.out.println("=============== g =================");

        /**Ищем значение*/
        System.out.println(map.containsValue("0"));
        System.out.println("=============== h =================");

        /**Количество элементов HashMap*/
        System.out.println(map.size());
        System.out.println("=============== i =================");

        /**Удаляем элемент по ключу и по значению*/
        map.remove("raspberry", "10");
        map.forEach((k, v) -> System.out.println(k + " = " + v));

    }

}
