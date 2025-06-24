package com.example.inopolis.mapper;

import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.model.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO entityToDto(StudentEntity entity);

    StudentEntity dtoToEntity(StudentDTO dto);

}
