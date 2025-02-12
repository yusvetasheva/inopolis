package com.example.inopolis.model;

import com.example.courses.model.CourseEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {
    Integer id;
    @NotBlank(message = "Поле fio не может быть пустым")
    @Pattern(regexp = "^[А-Яа-яA-Za-z\\s-]+$", message = "ФИО может содержать только буквы")
    String fio;
    @NotBlank(message = "Поле email не может быть пустым")
    @Email(message = "Некорректный формат email")
    String email;
    private List<CourseEntity> courses;
}
