package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO для передачи информации об адресе
 * Используется в модели магазина ({@link ShopDTO}) и склада ({@link StoreDTO})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDTO {
    /**
     * Город
     */
    @NotBlank(message = "city не может быть пустым у Address")
    @Pattern(regexp = "^[^0-9]*$", message = "city не может содержать цифр")
    String city;
    /**
     * Улица
     */
    @NotBlank(message = "street не может быть пустым у Address")
    String street;
    /**
     * Номер постройки
     */
    @NotBlank(message = "numberOfBuild не может быть пустым у Address")
    String numberOfBuild;
}
