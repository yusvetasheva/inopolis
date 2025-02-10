package com.example.inopolis.service;

import com.example.inopolis.model.CourseEnum;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.model.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> entityList = repository.findAll();
        if (entityList.isEmpty()) return new ArrayList<StudentDTO>();
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public void addStudent(StudentDTO studentDTO) {
        repository.save(dtoToEntity(studentDTO));
    }

    @Override
    public void updateStudent(Integer id, StudentDTO student) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existEntity = repository.findById(id);
        if (existEntity.isEmpty())
            throw new NoSuchElementException("Нет студента с id = " + id);
        existEntity.get().setCourseEnum(student.getCourseEnum());
        existEntity.get().setFio(student.getFio());
        existEntity.get().setEmail(student.getEmail());
        repository.save(existEntity.get());
    }

    @Override
    public void deleteStudent(Integer id) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        repository.deleteById(id);
    }

    public StudentDTO entityToDto(StudentEntity entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .email(entity.getEmail())
                .courseEnum(entity.getCourseEnum())
                .build();
    }

    public StudentEntity dtoToEntity(StudentDTO dto) {
        return StudentEntity.builder()
                .id(dto.getId())
                .fio(dto.getFio())
                .email(dto.getEmail())
                .courseEnum(dto.getCourseEnum())
                .build();
    }
}
