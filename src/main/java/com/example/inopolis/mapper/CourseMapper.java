package com.example.inopolis.mapper;

import com.example.inopolis.model.CourseEntity;
import com.example.inopolis.model.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity dtoToEntity (CourseDTO dto);
    CourseDTO entityToDto(CourseEntity entity);
}
