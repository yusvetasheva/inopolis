package com.example.model;

import com.example.model.dto.SellerDTO;
import com.example.model.entity.SellerEntity;

/**
 * Модель с возможными должностями сотрудников магазина
 * Используется в модели продавца ({@link SellerEntity} и {@link SellerDTO})
 * */
public enum Position {
    MINOR_SELLER,
    SELLER,
    MAJOR_SELLER,
    MANAGER
}
