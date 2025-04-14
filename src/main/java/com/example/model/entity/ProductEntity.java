package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Модель для передачи информации о товаре
 * Используется в модели склада ({@link StoreEntity})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {
    /**
     * Идентификатор товара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * Название товара
     */
    String name;
    /**
     * Описание товара
     */
    String description;
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