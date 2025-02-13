package com.example.inopolis.service;

import com.example.courses.dto.CourseDTO;
import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.model.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    private final StudentRepository repository;

    private final RestClient restClient;

    public StudentServiceImpl(StudentRepository repository, RestClient restClient) {
        this.repository = repository;
        this.restClient = restClient;
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
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        StudentEntity entity = studentMapper.dtoToEntity(studentDTO);

        /**Проверяем, существует ли добавляемый курс*/
        CourseDTO existCourse = null;
        try {
            existCourse = restClient.get()
                    .uri("/get-by-name?name=" + studentDTO.getCourse())
                    .retrieve()
                    .body(CourseDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        /**Если курса не существует, не сохраняем его в БД*/
        if (existCourse == null || existCourse.getIsActive()==null || !existCourse.getIsActive())
            entity.setCourse(null);

        repository.save(entity);
        return studentDTO;
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

        newStudent.setId(id);
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

    @Override
    public String addCourseToStudent(AddCourseToStudentRequest request) {
        if (request.getStudentId() == null)
            throw new IllegalArgumentException("studentId в методе addCourseToStudent не может быть null");
        if (request.getCourse() == null || request.getCourse().isEmpty())
            throw new IllegalArgumentException("course в методе addCourseToStudent не может быть null/empty");

        StudentEntity existStudent = repository.findById(request.getStudentId())
                .orElseThrow(() -> new NoSuchElementException("Нет студента с id = " + request.getStudentId()));

        //Проверяем, существует ли добавляемый курс
        CourseDTO existCourse = null;
        try {
            existCourse = restClient.get()
                    .uri("/get-by-name?name=" + request.getCourse())
                    .retrieve()
                    .body(CourseDTO.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (existCourse != null && existCourse.getIsActive()) {
            existStudent.setCourse(request.getCourse());
            repository.save(existStudent);
            return "Успех";
        } else return "Данный курс не активен";
    }

}
