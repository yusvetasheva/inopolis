package com.example.service;

import com.example.model.dto.ShopDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{
    @Override
    public Optional<ShopDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public ShopDTO create(ShopDTO seller) {
        return null;
    }

    @Override
    public ShopDTO update(Integer id, ShopDTO updatedShop) {
        return null;
    }
}
