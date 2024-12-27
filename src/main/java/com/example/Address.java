package com.example;

import lombok.*;

import java.io.Serializable;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {
    int homeNumber;
    String street;
}
