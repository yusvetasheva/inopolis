package com.example.inopolis.mapper;

import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.model.entity.business.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO entityToDto(StudentEntity entity);

    StudentEntity dtoToEntity(StudentDTO dto);

}
