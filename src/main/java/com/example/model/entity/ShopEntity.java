package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Модель для хранения информации о магазине
 * Используется в модели продавца ({@link SellerEntity})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shop")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopEntity {
    /**
     * Идентификатор магазина
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * Название магазина
     */
    @Column(name = "shop_name")
    String shopName;
    /**
     * Адрес магазина
     */
    @OneToOne(fetch = FetchType.LAZY, targetEntity = AddressEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressEntity address;
    /**
     * Склад магазина
     */
    @OneToOne(fetch = FetchType.LAZY, targetEntity = StoreEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    StoreEntity store;
    /**
     * Флаг активности. true - запись удалена
     */
    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        if (isDeleted == null) {
            isDeleted = false;
        }
    }
}
