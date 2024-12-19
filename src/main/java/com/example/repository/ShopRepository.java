package com.example.repository;

import com.example.model.Shop;

import java.util.List;

public interface ShopRepository {
    List<Shop> getAllShops();

    void addShop(Shop shop);

    void updateShop(Shop shop);

    void deleteShop(int id);
}
