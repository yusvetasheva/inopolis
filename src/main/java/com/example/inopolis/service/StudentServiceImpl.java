package com.example.inopolis.service;

import com.example.courses.model.dto.CourseDTO;
import com.example.inopolis.client.CourseRestClientApi;
import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.model.entity.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    StudentMapper studentMapper;

    StudentRepository repository;

    CourseRestClientApi restClient;

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return repository.findAll().map(studentMapper::entityToDto);
    }

    @Override
    public Flux<StudentDTO> getStudentsByCourse(String courseName) {
        if (courseName == null || courseName.isEmpty())
            return Flux.error(new IllegalArgumentException("courseName не может быть пустым в методе getStudentsByCourse"));

        return repository.findStudentsByCourse(courseName).map(studentMapper::entityToDto);
    }

    @Override
    public Flux<StudentDTO> getStudentWithSuchCoursesAmount(int amount) {
        return repository.findStudentWithSuchCoursesAmount(amount).map(studentMapper::entityToDto);
    }

    @Override
    public Mono<StudentDTO> registerStudent(StudentDTO studentDTO) {
        repository.save(studentMapper.dtoToEntity(studentDTO));
        return Mono.just(studentDTO);
    }

    @Override
    public Mono<StudentDTO> updateStudent(Integer id, StudentDTO newStudent) {
        if (id == null)
            return Mono.error(new IllegalArgumentException("id в методе updateStudent не может быть null"));

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Нет студента с id = " + id)))
                .flatMap(student -> {
                    student.setFio(newStudent.getFio());
                    student.setEmail(newStudent.getEmail());
                    return repository.save(student);
                })
                .map(studentMapper::entityToDto);

    }

    @Override
    public Mono<StudentDTO> deleteStudent(Integer id) {
        if (id == null) return
                Mono.error(new IllegalArgumentException("id в методе updateStudent не может быть null"));

        Mono<StudentEntity>existStudent = repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("В БД нет элемента с id = " + id)));

        repository.deleteById(id);
        return existStudent.map(studentMapper::entityToDto);
    }

    @Override
    public Mono<String> addCourseToStudent(AddCourseToStudentRequest request) {

        repository.findById(request.getStudentId())
                .switchIfEmpty(Mono.error(new NoSuchElementException("Нет студента с id = " + request.getStudentId())))
                .flatMap(exist->{

                    CourseDTO existCourse = null;
                    try {
                        existCourse = restClient.checkCourseIsExist(request.getCourse());
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }

                    if (existCourse != null && existCourse.getIsActive()) {

                        //Если студент уже записан на данный курс, выводим об этом сообщение
                        //Если нет - добавляем курс студенту
                        if (exist.getCourses().contains(request.getCourse()))
                            return Mono.just("Повтор");

                        exist.getCourses().add(request.getCourse());
                        repository.save(exist);
                        return Mono.just("Успех");
                    } else return Mono.just("Данный курс не активен");
                });

        return Mono.empty();

    }

}
