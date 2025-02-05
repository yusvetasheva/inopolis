package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "ORDER_NOTIFICATION")
public class OrderEntity {
    @Id
    @Column(name="id")
    Integer id;
    @Column(name="articul_tovara")
    String articulTovara;
    @Column(name="count")
    Integer count;
    @Column(name="sum")
    BigDecimal sum;
    @Column(name="\"date\"")
    LocalDate date;

    @Version
    private Integer version; // Поле для контроля версий
}
