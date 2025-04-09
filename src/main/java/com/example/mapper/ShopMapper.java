package com.example.mapper;

import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopDTO entityToDto(ShopEntity entity);
    ShopEntity dtoToEntity(ShopDTO dto);
}
