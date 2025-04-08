package com.example.service;

import com.example.model.dto.SellerDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService{
    @Override
    public Optional<SellerDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public SellerDTO create(SellerDTO seller) {
        return null;
    }

    @Override
    public SellerDTO update(Integer id, SellerDTO updatedSeller) {
        return null;
    }
}
