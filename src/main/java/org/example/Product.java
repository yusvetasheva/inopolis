package org.example;

import lombok.*;


public class Product {
    private long id;
    private String desc;
    private int cost;
    private int amount;

    public Product(long id, String desc, int cost, int amount){
        this.id = id;
        this.desc = desc;
        this.cost =cost;
        this.amount = amount;
    }

    public Product(){};

}