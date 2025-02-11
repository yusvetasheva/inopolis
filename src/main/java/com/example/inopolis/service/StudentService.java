package com.example.inopolis.service;

import com.example.inopolis.model.CourseDTO;
import com.example.inopolis.model.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    void addStudent(StudentDTO studentDTO);

    void updateStudent(Integer id, StudentDTO student);

    void deleteStudent(Integer id);

    void addCourseToStudent(Integer studentId, CourseDTO course);
}
