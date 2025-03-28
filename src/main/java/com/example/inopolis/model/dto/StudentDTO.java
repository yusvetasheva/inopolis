package com.example.inopolis.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {
    @NotBlank(message = "Поле fio не может быть пустым")
    @Pattern(regexp = "^[А-Яа-яA-Za-z\\s-]+$", message = "ФИО может содержать только буквы")
    String fio;
    @NotBlank(message = "Поле email не может быть пустым")
    @Email(message = "Некорректный формат email")
    String email;
}
