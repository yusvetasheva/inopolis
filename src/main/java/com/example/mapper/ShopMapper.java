package com.example.mapper;

import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import org.mapstruct.Mapper;

/**
 * Маппер для преобразования между {@link ShopEntity} и {@link ShopDTO}.
 * Используется MapStruct для автоматической генерации реализации.
 * Является Spring-компонентом.
 */
@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopDTO entityToDto(ShopEntity entity);

    ShopEntity dtoToEntity(ShopDTO dto);
}
