package com.example.inopolis.controller;

import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @AroundAnnotation
    @PostMapping(value = "/add-student")
    public Mono<ResponseEntity<StudentDTO>> addStudent(@RequestBody @Valid StudentDTO student) {
        return service.registerStudent(student).map(ResponseEntity::ok);
    }

    @GetMapping(value = "/get-with-such-course-amount/{amount}")
    public ResponseEntity<Flux<StudentDTO>> getStudentWithSuchCoursesAmount(@PathVariable int amount) {
        Flux<StudentDTO> studentList = service.getStudentWithSuchCoursesAmount(amount);

        return ResponseEntity.ok(studentList);
    }

    @PutMapping(value = "/update/{id}")
    public Mono<ResponseEntity<StudentDTO>> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        return service.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<ResponseEntity<StudentDTO>> deleteStudent(@PathVariable Integer id) {
        return service.deleteStudent(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Flux<StudentDTO>> getAllStudent() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @AroundAnnotation
    @GetMapping(value = "/get-student-by-course/{courseName}")
    public ResponseEntity<Flux<StudentDTO>> getStudentsByCourse(@PathVariable String courseName) {
        return new ResponseEntity<>(service.getStudentsByCourse(courseName), HttpStatus.OK);
    }

}
