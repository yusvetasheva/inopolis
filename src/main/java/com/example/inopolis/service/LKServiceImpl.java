package com.example.inopolis.service;

import com.example.inopolis.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LKServiceImpl implements LKService {

    private final StudentRepository repository;

    public LKServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> getStudentCourses(Integer studentId) {
        if (studentId == null)
            throw new IllegalArgumentException("studentId в методе getStudentCourses не может быть null");

        return repository.getCoursesById(studentId);
    }
}
