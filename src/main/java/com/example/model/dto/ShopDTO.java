package com.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Модель магазина")
public class ShopDTO {
    @NotBlank
    @Schema(description = "Название магазина", example = "Центральный филиал")
    String shopName;
    @Schema(description = "Адрес магазина")
    AddressDTO address;
}
