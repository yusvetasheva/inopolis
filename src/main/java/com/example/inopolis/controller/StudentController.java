package com.example.inopolis.controller;

import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(service.registerStudent(student));
    }

    @GetMapping(value = "/get-with-such-course-amount/{amount}")
    public ResponseEntity<List<StudentDTO>> getStudentWithSuchCoursesAmount(@PathVariable int amount){
        return ResponseEntity.ok(service.getStudentWithSuchCoursesAmount(amount));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        return new ResponseEntity<>(service.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Integer id) {
        return new ResponseEntity<>(service.deleteStudent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @AroundAnnotation
    @GetMapping(value = "/get-student-by-course/{courseName}")
    public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable String courseName) {
        return new ResponseEntity<>(service.getStudentsByCourse(courseName), HttpStatus.OK);
    }

}
