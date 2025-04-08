package com.example.model.dto;

import com.example.model.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Модель продавца")
public class SellerDTO {
    @NotBlank(message = "fio не может быть пустым у Seller")
    @Schema(description = "ФИО продавца", example = "Иванов Иван Саныч")
    String fio;
    @NotNull(message = "position не может быть пустым у Seller")
    @Schema(description = "Текущая занимаемая должность")
    Position currPosition;
    @Schema(description = "Магазин, в котором работает продавец")
    ShopDTO shop;
    @NotNull(message = "salary не может быть пустым у Seller")
    @Schema(description = "Зарплата продавца в российских рублях", example = "5000.00")
    Double salary;
}