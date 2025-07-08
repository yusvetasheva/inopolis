package com.example.inopolis.service;

import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.dto.StudentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<StudentDTO> getAllStudents();

    Flux<StudentDTO> getStudentsByCourse(String courseName);

    Flux<StudentDTO> getStudentWithSuchCoursesAmount(int amount);

    Mono<StudentDTO> registerStudent(StudentDTO studentDTO);

    Mono<StudentDTO> updateStudent(Integer id, StudentDTO student);

    Mono<StudentDTO> deleteStudent(Integer id);

    Mono<String> addCourseToStudent(AddCourseToStudentRequest request);

}
