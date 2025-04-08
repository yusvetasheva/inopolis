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
@Schema(description = "Модель товара")
public class ProductDTO {
    @NotBlank(message = "name не может быть пустым у Product")
    @Schema(description = "Название товара", example = "Ноутбук")
    String name;
    @NotBlank(message = "name не может быть пустым у Product")
    @Schema(description = "Описание товара", example = "Новый, дорогой")
    String description;
}