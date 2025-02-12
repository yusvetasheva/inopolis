package com.example.inopolis.controller;

import com.example.courses.dto.CourseDTO;
import com.example.inopolis.model.AddCourseToSyudentRequest;
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

    @PostMapping(value = "/add-student")
    public ResponseEntity<String> addStudent(@RequestBody @Valid StudentDTO student) {
        service.registerStudent(student);
        return ResponseEntity.ok("Студент успешно добавлен");
    }

    @PostMapping(value = "/add-course")
    public ResponseEntity<String> addCourseToStudent(@RequestBody AddCourseToSyudentRequest request){
        String result = service.addCourseToStudent(request);
        if(result.equals("Успех"))
        return ResponseEntity.ok("Курс " + request.getCourse() + " успешно добавлен студенту с id = " + request.getStudentId());
        else
            return ResponseEntity.badRequest().body(result);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
