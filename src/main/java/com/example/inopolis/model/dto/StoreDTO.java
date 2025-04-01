package com.example.inopolis.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreDTO {
    @NotBlank
    String name;
    @NotBlank
    String desc;
}
