package com.example.inopolis.controller;

import com.example.inopolis.model.CourseDTO;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {
    @Autowired
    StudentServiceImpl service;

    @PostMapping(value = "/add-student")
    public ResponseEntity<String> addStudent(@RequestBody @Valid StudentDTO student) {
        service.addStudent(student);
        return ResponseEntity.ok("Студент успешно добавлен");
    }

    @PostMapping(value = "/add-course/{studentId}")
    public ResponseEntity<String> addCourseToStudent(@PathVariable Integer studentId, @RequestBody CourseDTO course){
        service.addCourseToStudent(studentId, course);
        return ResponseEntity.ok("Курс успешно добавлен");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        service.updateStudent(id, student);
        return ResponseEntity.ok("Студент успешно обновлен");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
        service.deleteStudent(id);
        return ResponseEntity.ok("Студент успешно удален");
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        return ResponseEntity.of(Optional.of(service.getAllStudents()));
    }

}
