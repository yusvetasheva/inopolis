package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    @NotBlank(message = "name не может быть пустым у Product")
    String name;
    @NotBlank(message = "name не может быть пустым у Product")
    String description;
}