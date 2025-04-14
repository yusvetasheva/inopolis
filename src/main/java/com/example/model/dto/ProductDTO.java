package com.example.model.dto;

import com.example.model.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO для передачи информации о товаре
 * Используется в модели склада ({@link StoreEntity})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    /**
     * Название товара
     * */
    @NotBlank(message = "name не может быть пустым у Product")
    String name;
    /**
     * Описание товара
     * */
    @NotBlank(message = "name не может быть пустым у Product")
    String description;
}