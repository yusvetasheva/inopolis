package com.example.service;

import com.example.model.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Optional<ProductDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public ProductDTO create(ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO updatedProduct) {
        return null;
    }
}
