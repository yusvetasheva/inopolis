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
@Entity
@Table(name = "Store")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressEntity address;
    Integer capacity;
    Integer fullness;
    @Column(name = "is_deleted")
    Boolean isDeleted = false;
    @OneToMany(targetEntity = ProductEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "shop_id")
    List<ProductEntity> products = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}