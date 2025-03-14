package com.example.inopolis.controller;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/student")
public class CourseController {

    private final StudentService service;

    public CourseController(StudentService service) {
        this.service = service;
    }

    @AroundAnnotation
    @PostMapping(value = "/add-course")
    public Mono<ResponseEntity<String>> addCourseToStudent(@RequestBody AddCourseToStudentRequest request) {
        return service.addCourseToStudent(request).map(ResponseEntity::ok);
    }

    @PostMapping(value = "/add-comment")
    public Mono<ResponseEntity<CourseDTO>> addCommentToCourse(@RequestBody AddCommentToCourseRequest request) {
        return service.addCommentToCourse(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }
}
