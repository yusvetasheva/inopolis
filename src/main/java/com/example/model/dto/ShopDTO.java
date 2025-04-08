package com.example.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopDTO {
    @NotBlank
    String shopName;
    AddressDTO address;
    StoreDTO store;
    List<ProductDTO> products = new ArrayList<>();
}
