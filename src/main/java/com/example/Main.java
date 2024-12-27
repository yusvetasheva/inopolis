package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.*;
import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Person person = new Person(List.of(new Address(1, "Moscow")), "124", "201@mail.ru");

        //JSON serial
        ObjectMapper mapper = new ObjectMapper();
        String jsonPerson = mapper.writeValueAsString(person);

        Person newPersom = mapper.readValue(jsonPerson, Person.class);

        BufferedWriter writer = new BufferedWriter(new FileWriter("json.txt"));
        writer.write(jsonPerson);
        writer.close();

        System.out.println(newPersom);
    }
}
