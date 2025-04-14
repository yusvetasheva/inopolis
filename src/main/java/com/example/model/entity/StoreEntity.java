package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель склада
 * Используется в модели магазина ({@link ShopEntity})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Store")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreEntity {
    /**
     * Идентификатор склада
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * Адрес склада
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    AddressEntity address;
    /**
     * Вместимость склада в единицах товара
     */
    Integer capacity;
    /**
     * Заполненность склада в единицах товара
     */
    Integer fullness;
    /**
     * Флаг активности. true - запись удалена
     */
    @Column(name = "is_deleted")
    Boolean isDeleted = false;
    /**
     * Набор товаров, хранимых на складе
     */
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