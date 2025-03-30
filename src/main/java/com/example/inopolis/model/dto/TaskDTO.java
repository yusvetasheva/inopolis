package com.example.inopolis.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDTO {
    @NotBlank
    String name;
    @NotBlank
    String description;
    LocalDate createdDate;
}
