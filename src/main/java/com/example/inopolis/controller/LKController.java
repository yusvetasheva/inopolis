package com.example.inopolis.controller;

import com.example.inopolis.service.LKService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/lk")
public class LKController {
    private final LKService lkService;

    public LKController(LKService lkService) {
        this.lkService = lkService;
    }

    @GetMapping(value = "/get-all-courses/{studentId}")
    ResponseEntity<List<String>> getStudentCourses(@PathVariable Integer studentId){
        return  ResponseEntity.ok(lkService.getStudentCourses(studentId));
    }
}
