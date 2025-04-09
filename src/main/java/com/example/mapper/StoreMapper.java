package com.example.mapper;

import com.example.model.dto.StoreDTO;
import com.example.model.entity.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreDTO entityToDto(StoreEntity entity);
    StoreEntity dtoToEntity(StoreDTO dto);
}
