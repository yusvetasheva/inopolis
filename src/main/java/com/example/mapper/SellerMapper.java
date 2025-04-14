package com.example.mapper;

import com.example.model.dto.SellerDTO;
import com.example.model.entity.SellerEntity;
import org.mapstruct.Mapper;

/**
 * Маппер для преобразования между {@link SellerEntity} и {@link SellerDTO}.
 * Используется MapStruct для автоматической генерации реализации.
 * Является Spring-компонентом.
 */
@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerDTO entityToDto(SellerEntity entity);

    SellerEntity dtoToEntity(SellerDTO dto);
}
