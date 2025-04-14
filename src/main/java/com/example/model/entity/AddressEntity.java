package com.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Модель для передачи информации об адресе
 * Используется в модели магазина ({@link ShopEntity}) и склада ({@link StoreEntity})
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity {
    /**
     * Идентификатор адреса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * Город
     */
    @Column(name = "city", nullable = false)
    String city;
    /**
     * Улица
     */
    String street;
    @Column(name = "number_of_build")
    /**
     * Номер постройки
     */
    String numberOfBuild;
    /**
     * Флаг активности записи (true - запись удалена)
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
