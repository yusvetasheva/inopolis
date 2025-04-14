package com.example.model.dto;

import com.example.model.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO для передачи информации о продавце
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerDTO {
    /**
     * ФИО продавца
     */
    @NotBlank(message = "fio не может быть пустым у Seller")
    String fio;
    /**
     * Текущая должность
     */
    @NotNull(message = "position не может быть пустым у Seller")
    Position currPosition;
    /**
     * Магазин, в котором работает продавец
     */
    ShopDTO shop;
    @NotNull(message = "salary не может быть пустым у Seller")
    /**
     * Заработная плата в российских рублях
     */
    Double salary;
}