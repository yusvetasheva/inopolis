package com.example.inopolis.mapper;

import com.example.inopolis.model.dto.StoreDTO;
import com.example.inopolis.model.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreMapper {

    StoreDTO entityToDto(StoreEntity entity);

    StoreEntity dtoToEntity(StoreDTO dto);

}
