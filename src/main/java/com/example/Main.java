package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        String input = "Hello world! Hello Java. Java is fun.";
        //[Hello=2, Java=2, is=1, fun=1, world=1]

        String [] array = input.replace("!","").replace(".", "").toLowerCase().split(" ");

        Map<String, Integer> map = Stream.of(array).collect(
                Collectors.toMap(
                        i->i,
                        i->1,
                        Integer::sum
                )
        );

        // Сортировка по значению в порядке убывания
        Map<String, Integer> sortedMap  = map.entrySet().stream()
                        .sorted((e1, e2)->e2.getValue().compareTo(e1.getValue()))
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (oldValue, newValue)->oldValue,
                                        LinkedHashMap::new
                                ));

        sortedMap.forEach((k,v)->System.out.println(k + " " + v.toString()));


    }
}
