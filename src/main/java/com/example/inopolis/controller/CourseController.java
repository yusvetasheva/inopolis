package com.example.inopolis.controller;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/student")
public class CourseController {

    private final StudentService service;

    public CourseController(StudentService service) {
        this.service = service;
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

    @GetMapping(value = "/get-course-like")
    public ResponseEntity<List<StudentDTO>> getStudentsWithCoursesLike(@RequestParam String courseName){
        return ResponseEntity.ok(service.getStudentsWithCoursesLike(courseName));
    }

    @PostMapping(value = "/add-comment")
    public ResponseEntity<CourseDTO> addCommentToCourse(@Valid @RequestBody AddCommentToCourseRequest request){
        return new ResponseEntity<>(service.addCommentToCourse(request), HttpStatus.OK);
    }
}
