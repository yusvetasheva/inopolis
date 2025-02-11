package com.example.inopolis.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {
    private Long id;
    @NotNull(message = "Имя курса не может быть пустым")
    private CourseEnum course;
}
