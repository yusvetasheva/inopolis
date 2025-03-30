package com.example.inopolis.service;

import com.example.inopolis.model.entity.StudentEntity;
import com.example.inopolis.repository.StudentRepository;
import com.example.inopolis.security.HashPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LKServiceImpl implements LKService {

    private final StudentRepository repository;

    private final JdbcTemplate jdbcTemplate;

    private final HashPasswordService hashPasswordService;

    public LKServiceImpl(StudentRepository repository, JdbcTemplate jdbcTemplate, HashPasswordService hashPasswordService) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
        this.hashPasswordService = hashPasswordService;
    }

    @Override
    public List<String> getStudentCourses(Integer studentId) {
        if (studentId == null)
            throw new IllegalArgumentException("studentId в методе getStudentCourses не может быть null");

        return repository.getCoursesById(studentId);
    }

    @Override
    public ResponseEntity<String> registerStudent(String email, String password) {
        String hashedPassword = hashPasswordService.hashPassword(password);

        Optional<StudentEntity> exist = repository.getStudentByEmail(email);
        if (exist.isEmpty()) {
            jdbcTemplate.update(
                    "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)",
                    email, hashedPassword, true
            );

            jdbcTemplate.update(
                    "INSERT INTO authorities (username, authority) VALUES (?, ?)",
                    email, "ROLE_USER"
            );

            repository.save(StudentEntity.builder().email(email).passwordHash(hashedPassword).build());

            return ResponseEntity.ok("Студент с email = " + email + " успешно зарегистрирован");
        }

        return ResponseEntity.badRequest().body("Студент с email = " + email + " уже существует");
    }

}
