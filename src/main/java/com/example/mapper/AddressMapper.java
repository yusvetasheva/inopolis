package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import org.mapstruct.Mapper;

/**
 * Маппер для преобразования между {@link AddressEntity} и {@link AddressDTO}.
 * Используется MapStruct для автоматической генерации реализации.
 * Является Spring-компонентом.
 */
@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO entityToDto(AddressEntity entity);

    AddressEntity dtoToEntity(AddressDTO dto);

}
