package com.example.inopolis.controller;

import com.example.inopolis.model.dto.StudentRegistrationDTO;
import com.example.inopolis.service.LKService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/lk")
public class LKController {
    private final LKService lkService;

    public LKController(LKService lkService) {
        this.lkService = lkService;
    }

    @GetMapping(value = "/get-all-courses/{studentId}")
    ResponseEntity<List<String>> getStudentCourses(@PathVariable Integer studentId) {
        return ResponseEntity.ok(lkService.getStudentCourses(studentId));
    }

    @PostMapping(value = "/register-db")
    ResponseEntity<String> registerWithDB(@RequestBody @Valid StudentRegistrationDTO registrationDTO) {
        return lkService.registerStudent(registrationDTO.getEmail(), registrationDTO.getPassword());
    }
}
