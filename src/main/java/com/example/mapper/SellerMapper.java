package com.example.mapper;

import com.example.model.dto.SellerDTO;
import com.example.model.entity.SellerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerDTO entityToDto(SellerEntity entity);
    SellerEntity dtoToEntity(SellerDTO dto);
}
