package com.example.mapper;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO entityToDto(ProductEntity entity);

    ProductEntity dtoToEntity(ProductDTO dto);
}
