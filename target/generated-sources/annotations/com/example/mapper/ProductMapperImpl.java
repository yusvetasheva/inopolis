package com.example.mapper;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-14T21:25:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO entityToDto(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( entity.getName() );
        productDTO.setDescription( entity.getDescription() );

        return productDTO;
    }

    @Override
    public ProductEntity dtoToEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( dto.getName() );
        productEntity.setDescription( dto.getDescription() );

        return productEntity;
    }
}
