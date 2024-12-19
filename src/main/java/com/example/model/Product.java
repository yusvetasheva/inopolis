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
public class Product {
    int id;
    String productName;
    String description;
    Integer shopId;

    public Product(String productName, String description, Integer shopId){
        this.productName = productName;
        this.description = description;
        this.shopId = shopId;
    }
}