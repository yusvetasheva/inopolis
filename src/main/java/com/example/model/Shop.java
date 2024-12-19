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
public class Shop {
    int id;
    Integer addressId;
    Integer storeId;
    String shopName;

    public Shop (Integer addressId, Integer storeId, String shopName){
        this.addressId = addressId;
        this.storeId = storeId;
        this.shopName = shopName;
    }
}
