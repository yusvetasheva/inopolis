package com.example;

import lombok.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {
    List<Address> addressList;
    String age;
    String email;

    @Serial
    @SneakyThrows
    private void writeObject (ObjectOutputStream outputStream){
        outputStream.defaultWriteObject();
        String bigEmail = email.toUpperCase();
        outputStream.writeObject(bigEmail);
    }

    @Serial
    @SneakyThrows
    private void readObject (ObjectInputStream inputStream){
        inputStream.defaultReadObject();
        email = (String) inputStream.readObject();
    }
}
