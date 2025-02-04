package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class OrderDTO {
    @JsonProperty(value = "id")
    Integer id;
    @JsonProperty(value = "articulTovara")
    String articulTovara;
    @JsonProperty(value = "count")
    @NotNull
    Integer count;
    @JsonProperty(value = "sum")
    @NotNull
    BigDecimal sum;
    @JsonProperty(value = "date")
    LocalDate date;
}
