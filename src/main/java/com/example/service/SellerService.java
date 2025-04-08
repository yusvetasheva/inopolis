package com.example.service;

import com.example.model.dto.SellerDTO;

import java.util.Optional;
/**
 * Сервис для работы с продавцами
 * */
public interface SellerService {

    Optional<SellerDTO> getById(Integer id);

    void deleteById(Integer id);

    SellerDTO create(SellerDTO seller);

    SellerDTO update(Integer id, SellerDTO updatedSeller);
}
