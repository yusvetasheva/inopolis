package com.example.mapper;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.ProductEntity;
import org.mapstruct.Mapper;
/**
 * Маппер для преобразования между {@link ProductEntity} и {@link ProductDTO}.
 * Используется MapStruct для автоматической генерации реализации.
 * Является Spring-компонентом.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO entityToDto(ProductEntity entity);

    ProductEntity dtoToEntity(ProductDTO dto);
}
