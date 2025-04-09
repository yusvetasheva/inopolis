package com.example.service;

import com.example.mapper.ProductMapper;
import com.example.model.dto.ProductDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ProductEntity;
import com.example.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;
    ProductMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> getById(Integer id) {
        return Optional.of(mapper.entityToDto(findEntityById(id)));
    }

    @Override
    public void deleteById(Integer id) {
        ProductEntity exist = findEntityById(id);
        exist.setIsDeleted(true);
        repository.save(exist);
    }

    @Override
    public ProductDTO create(ProductDTO product) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(product)));
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO updatedProduct) {
        ProductEntity exist = findEntityById(id);

        if (updatedProduct.getName() != null) exist.setName(updatedProduct.getName());
        if (updatedProduct.getDescription() != null) exist.setDescription(updatedProduct.getDescription());

        ProductEntity updated = repository.save(exist);

        return mapper.entityToDto(updated);
    }

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private ProductEntity findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product с id = " + id + "не найден"));
    }


}
