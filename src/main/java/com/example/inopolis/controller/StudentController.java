package com.example.inopolis.controller;

import com.example.courses.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
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
        return ResponseEntity.ok(service.registerStudent(student));
    }

    @AroundAnnotation
    @PostMapping(value = "/add-course")
    public ResponseEntity<String> addCourseToStudent(@RequestBody AddCourseToStudentRequest request) {
        String result = service.addCourseToStudent(request);
        if (result.equals("Успех"))
            return ResponseEntity.ok("Курс " + request.getCourse() + " успешно добавлен студенту с id = " + request.getStudentId());
        else if (result.equals("Повтор"))
            return ResponseEntity.ok("Студент с id = " + request.getStudentId() + " уже записан на курс " + request.getCourse());
        else
            return ResponseEntity.badRequest().body(result);
    }

    @GetMapping(value = "/get-with-such-course-amount/{amount}")
    public ResponseEntity<List<StudentDTO>> getStudentWithSuchCoursesAmount(@PathVariable int amount){
        return ResponseEntity.ok(service.getStudentWithSuchCoursesAmount(amount));
    }

    @GetMapping(value = "/get-course-like")
    public ResponseEntity<List<StudentDTO>> getStudentsWithCoursesLike(@RequestParam String courseName){
        return ResponseEntity.ok(service.getStudentsWithCoursesLike(courseName));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student) {
        return new ResponseEntity<>(service.updateStudent(id, student), HttpStatus.OK);
    }

    @PostMapping(value = "/add-comment")
    public ResponseEntity<CourseDTO> addCommentToCourse(@Valid @RequestBody AddCommentToCourseRequest request){
        return new ResponseEntity<>(service.addCommentToCourse(request), HttpStatus.OK);
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
