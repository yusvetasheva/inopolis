package com.example.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель склада
 * Используется в модели магазина ({@link ShopDTO})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreDTO {
    /**
     * Адрес склада
     */
    AddressDTO address;
    /**
     * Вместимость склада в единицах товара
     */
    Integer capacity;
    /**
     * Заполненность склада в единицах товара
     */
    Integer fullness;
    /**
     * Набор товаров, хранимых на складе
     */
    List<ProductDTO> products = new ArrayList<>();
}