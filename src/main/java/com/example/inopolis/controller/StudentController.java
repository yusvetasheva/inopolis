package com.example.inopolis.controller;

import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @AroundAnnotation
    @PostMapping(value = "/add-student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody @Valid StudentDTO student) {
        return ResponseEntity.of(Optional.ofNullable(service.registerStudent(student)));
    }

    @AroundAnnotation
    @PostMapping(value = "/add-course")
    public ResponseEntity<String> addCourseToStudent(@RequestBody AddCourseToStudentRequest request) {
        String result = service.addCourseToStudent(request);
        if (result.equals("Успех"))
            return ResponseEntity.ok("Курс " + request.getCourse() + " успешно добавлен студенту с id = " + request.getStudentId());
        else
            return ResponseEntity.badRequest().body(result);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        return ResponseEntity.of(Optional.ofNullable(service.updateStudent(id, student)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer id) {
        return ResponseEntity.of(Optional.ofNullable(service.deleteStudent(id)));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        return ResponseEntity.of(Optional.ofNullable(service.getAllStudents()));
    }

    @AroundAnnotation
    @GetMapping(value = "/get-student-by-course/{courseName}")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable String courseName) {
        return ResponseEntity.of(Optional.ofNullable(service.getStudentsByCourse(courseName)));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
