package com.example.service;

import com.example.model.dto.StoreDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{
    @Override
    public Optional<StoreDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public StoreDTO create(StoreDTO shop) {
        return null;
    }

    @Override
    public StoreDTO update(Integer id, StoreDTO updatedStore) {
        return null;
    }
}
