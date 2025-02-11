package com.example.inopolis.mapper;

import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO entityToDto(StudentEntity entity);

    StudentEntity dtoToEntity(StudentDTO dto);

}
