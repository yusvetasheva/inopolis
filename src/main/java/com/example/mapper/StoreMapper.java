package com.example.mapper;

import com.example.model.dto.StoreDTO;
import com.example.model.entity.StoreEntity;
import org.mapstruct.Mapper;

/**
 * Маппер для преобразования между {@link StoreDTO} и {@link StoreEntity}.
 * Используется MapStruct для автоматической генерации реализации.
 * Является Spring-компонентом.
 */
@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreDTO entityToDto(StoreEntity entity);

    StoreEntity dtoToEntity(StoreDTO dto);
}
