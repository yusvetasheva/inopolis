package com.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Модель склада")
public class StoreDTO {
    @Schema(description = "Адрес склада (может совпадать с адресом магазина)")
    AddressDTO address;
    @Schema(description = "Вместимость склада в единицах товара", example = "17363")
    Integer capacity;
    @Schema(description = "Текущая заполненность склада в единицах товара", example = "183")
    Integer fullness;
    @Schema(description = "Список товаров, хранимых на складе")
    List<ProductDTO> products = new ArrayList<>();
}