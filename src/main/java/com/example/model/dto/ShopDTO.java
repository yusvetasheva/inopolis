package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO для передачи информации о магазине
 * Используется в модели продавца ({@link SellerDTO})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopDTO {
    /**
     * Название магазина
     */
    @NotBlank
    String shopName;
    /**
     * Адрес магазина
     */
    AddressDTO address;
    /**
     * Склад магазина
     */
    StoreDTO store;
}
