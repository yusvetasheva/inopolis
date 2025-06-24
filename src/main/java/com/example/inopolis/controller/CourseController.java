package com.example.inopolis.controller;

import com.example.inopolis.aspect.AroundAnnotation;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.service.StudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/student")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CourseController {

    StudentService service;

    @AroundAnnotation
    @PostMapping(value = "/add-course")
    public Mono<ResponseEntity<String>> addCourseToStudent(@RequestBody @Valid AddCourseToStudentRequest request) {
        return service.addCourseToStudent(request).map(ResponseEntity::ok);
    }
}
