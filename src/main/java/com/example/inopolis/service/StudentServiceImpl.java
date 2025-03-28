package com.example.inopolis.service;

import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.model.entity.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> entityList = repository.findAll();
        if (entityList.isEmpty()) return new ArrayList<StudentDTO>();
        return entityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(String courseName) {
        if (courseName == null || courseName.isEmpty())
            throw new IllegalArgumentException("courseName не может быть пустым в методе getStudentsByCourse");

        List<StudentEntity> studentOnCourseList = repository.findStudentsByCourse(courseName);

        return studentOnCourseList.isEmpty() ?
                Collections.emptyList() :
                studentOnCourseList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentWithSuchCoursesAmount(int amount) {
        List<StudentEntity> studentEntityList =
                repository.findStudentWithSuchCoursesAmount(amount);

        if (studentEntityList == null || studentEntityList.isEmpty()) return Collections.emptyList();

        return studentEntityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public List<StudentDTO> getStudentsWithCoursesLike(String course) {
        List<StudentEntity> studentEntityList = repository.findStudentsWithCoursesLike(course);

        if (studentEntityList == null || studentEntityList.isEmpty()) return Collections.emptyList();
        return studentEntityList.stream().map(studentMapper::entityToDto).toList();
    }

    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        StudentEntity entity = studentMapper.dtoToEntity(studentDTO);
        repository.save(entity);
        return studentMapper.entityToDto(entity);
    }

    @Override
    public StudentDTO updateStudent(Integer id, StudentDTO newStudent) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existEntity = repository.findById(id);
        if (existEntity.isEmpty())
            throw new NoSuchElementException("Нет студента с id = " + id);
        ;

        existEntity.get().setFio(newStudent.getFio());
        existEntity.get().setEmail(newStudent.getEmail());
        repository.save(existEntity.get());
        return newStudent;
    }

    @Override
    public StudentDTO deleteStudent(Integer id) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existStudent = repository.findById(id);
        if (existStudent.isEmpty()) throw new NoSuchElementException("В БД нет элемента с id = " + id);
        repository.deleteById(id);
        return studentMapper.entityToDto(existStudent.get());
    }

}
