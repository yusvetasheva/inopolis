package com.example.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Модель адреса")
public class AddressDTO {
    @NotBlank(message = "city не может быть пустым у Address")
    @Pattern(regexp = "^[^0-9]*$", message = "city не может содержать цифр")
    @Schema(description = "Название города", example = "Москва")
    String city;
    @Schema(description = "Название улицы", example = "Первомайская")
    @NotBlank(message = "street не может быть пустым у Address")
    String street;
    @Schema(description = "Номер здания", example = "2 корпус 1")
    @NotBlank(message = "numberOfBuild не может быть пустым у Address")
    String numberOfBuild;
}
