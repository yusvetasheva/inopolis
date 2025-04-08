package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDTO {
    @NotBlank(message = "city не может быть пустым у Address")
    @Pattern(regexp = "^[^0-9]*$", message = "city не может содержать цифр")
    String city;
    @NotBlank(message = "street не может быть пустым у Address")
    String street;
    @NotBlank(message = "numberOfBuild не может быть пустым у Address")
    String numberOfBuild;
}
