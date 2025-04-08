package com.example.model.dto;

import com.example.model.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerDTO {
    @NotBlank(message = "fio не может быть пустым у Seller")
    String fio;
    @NotNull(message = "position не может быть пустым у Seller")
    Position currPosition;
    ShopDTO shop;
    @NotNull(message = "salary не может быть пустым у Seller")
    Double salary;
}