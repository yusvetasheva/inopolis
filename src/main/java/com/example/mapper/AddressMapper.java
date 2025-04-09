package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO entityToDto(AddressEntity entity);

    AddressEntity dtoToEntity(AddressDTO dto);

}
