package com.example.service;

import com.example.model.dto.ShopDTO;

import java.util.Optional;
/**
 * Сервис для работы с магазином
 * */
public interface ShopService {
    Optional<ShopDTO> getById(Integer id);

    void deleteById(Integer id);

    ShopDTO create(ShopDTO seller);

    ShopDTO update(Integer id, ShopDTO updatedShop);
}
