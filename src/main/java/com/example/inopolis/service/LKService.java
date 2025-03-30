package com.example.inopolis.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LKService {
    List<String> getStudentCourses(Integer studentId);
    ResponseEntity<String> registerStudent(String email, String password);
    ResponseEntity<String> registerUserWithDB(String email, String password);
}
