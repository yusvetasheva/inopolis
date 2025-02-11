package com.example.inopolis.service;

import com.example.inopolis.mapper.CourseMapper;
import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.CourseEntity;
import com.example.inopolis.model.CourseDTO;
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

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

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
    public void addStudent(StudentDTO studentDTO) {
        repository.save(studentMapper.dtoToEntity(studentDTO));
    }

    @Override
    public void updateStudent(Integer id, StudentDTO newStudent) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        Optional<StudentEntity> existEntity = repository.findById(id);
        if (existEntity.isEmpty())
            throw new NoSuchElementException("Нет студента с id = " + id);
        existEntity.get().setCourses(newStudent.getCourses());
        existEntity.get().setFio(newStudent.getFio());
        existEntity.get().setEmail(newStudent.getEmail());
        repository.save(existEntity.get());
    }

    @Override
    public void deleteStudent(Integer id) {
        if (id == null) throw new IllegalArgumentException("id в методе updateStudent не может быть null");
        repository.deleteById(id);
    }

    @Override
    public void addCourseToStudent(Integer studentId, CourseDTO courseDTO) {
        if (studentId == null)
            throw new IllegalArgumentException("studentId в методе addCourseToStudent не может быть null");
        if (courseDTO == null || courseDTO.getCourse() == null)
            throw new IllegalArgumentException("course в методе addCourseToStudent не может быть null");

        StudentEntity existStudent = repository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Нет студента с id = " + studentId));

        existStudent.getCourses().add(courseMapper.dtoToEntity(courseDTO));
        repository.save(existStudent);
    }

}
