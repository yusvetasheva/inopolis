package com.example.model.entity;

import com.example.model.Position;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
/**
 * Модель для хранения информации о продавце
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerEntity {
    /**
     * Идентификатор продавца
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * ФИО продавца
     */
    String fio;
    /**
     * Текущая должность
     */
    @Column(name = "current_position")
    @Enumerated(EnumType.STRING)
    Position currPosition;
    /**
     * Магазин, в котором работает продавец
     */
    @OneToOne(fetch = FetchType.LAZY, targetEntity = ShopEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    ShopEntity shop;
    /**
     * Заработная плата в российских рублях
     */
    Double salary;
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