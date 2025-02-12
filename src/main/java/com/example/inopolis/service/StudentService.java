package com.example.inopolis.service;

import com.example.courses.dto.CourseDTO;
import com.example.inopolis.model.AddCourseToSyudentRequest;
import com.example.inopolis.model.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    void registerStudent(StudentDTO studentDTO);

    void updateStudent(Integer id, StudentDTO student);

    void deleteStudent(Integer id);

    String addCourseToStudent(AddCourseToSyudentRequest request);
}
