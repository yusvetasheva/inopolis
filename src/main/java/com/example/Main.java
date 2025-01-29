package com.example;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        cycleGrayCode(2).limit(10).forEach(System.out::println);
        readFile(6);

    }

    /**
     * Напишите метод,возвращающий стрим чисел,соответствующих n-битным кодам Грея.
     */
    public static Stream<Integer> cycleGrayCode(int n) {

        List<Integer> codes = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            codes.add(i ^ (i >> 1));
        }

        // Создаем бесконечный поток, который циклично повторяет коды Грея
        return Stream.generate(new Supplier<Integer>() {
            private int index = 0;

            @Override
            public Integer get() {
                int code = codes.get(index);
                index = (index + 1) % codes.size();
                return code;
            }
        });
    }

    /**
     * Напишите программу, читающую из файла текст в кодировке UTF-8, которая
     * посчитает в тексте частоту появления слов, и в конце выводит в файл n наиболее часто встречающихся слов.
     * Решить без циклов и условных операторов
     */

    @SneakyThrows
    public static void readFile(int n) {
        File fileInput = new File("source.txt");
        File fileOutput = new File("result.txt");

        //слово->количество повторов
        Map<String, Integer> result = Files.lines(Paths.get(fileInput.getAbsolutePath()))
                .flatMap(s -> Stream.of(s.split(" ")))
                .collect(Collectors.toMap(
                        i -> i,
                        i -> 1,
                        Integer::sum
                ));


        //Сортируем папу по убиванию value (частоты повторений слова) и берем первые n элементов
        List<String> worlds = result.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed() //сортировка по частоте
                        .thenComparing(Map.Entry.<String, Integer>comparingByKey())) //сортировка по слову
                .map(Map.Entry::getKey)//взяли слова
                .limit(n)
                .toList();

        // Записываем результат в файл с разделителем между словами
        try (FileWriter fileWriter = new FileWriter(fileOutput)) {
            worlds.forEach(word -> {
                        try {
                            fileWriter.write(word + System.lineSeparator()); // Добавляем перенос строки
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }


}
