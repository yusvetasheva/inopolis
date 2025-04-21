package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Shop")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "shop_name")
    String shopName;
    @OneToOne(fetch = FetchType.LAZY, targetEntity = AddressEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressEntity address;
    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}
