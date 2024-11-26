package org.example;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    private String desc;
    private int cost;
    private int amount;

}