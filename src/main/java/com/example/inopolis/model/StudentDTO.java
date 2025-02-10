package com.example.inopolis.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {
    @NotNull(message = "Поле id не может быть пустым")
    Integer id;
    @NotBlank(message = "Поле fio не может быть пустым")
    String fio;
    @NotBlank(message = "Поле email не может быть пустым")
    String email;
    @NotNull(message = "Поле courseEnum не может быть пустым")
    CourseEnum courseEnum;
}
