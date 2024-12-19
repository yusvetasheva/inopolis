package com.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seller {
    int id;
    String fio;
    String custPosition;
    Integer storeId;
    Integer salary;

    public Seller(String fio, String custPosition, Integer storeId, Integer salary) {
        this.fio = fio;
        this.custPosition = custPosition;
        this.storeId = storeId;
        this.salary = salary;
    }
}