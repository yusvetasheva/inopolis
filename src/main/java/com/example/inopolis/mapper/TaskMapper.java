package com.example.inopolis.mapper;

import com.example.inopolis.model.dto.TaskDTO;
import com.example.inopolis.model.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO entityToDto(TaskEntity entity);

    TaskEntity dtoToEntity(TaskDTO dto);

}
