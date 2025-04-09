package com.example.model.entity;

import com.example.model.Position;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fio;
    @Column(name = "current_position")
    @Enumerated(EnumType.STRING)
    Position currPosition;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ShopEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    ShopEntity shop;
    Double salary;
    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}