package com.example.inopolis.service;

import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    List<StudentDTO> getStudentsByCourse(String courseName);

    StudentDTO registerStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Integer id, StudentDTO student);

    StudentDTO deleteStudent(Integer id);

    String addCourseToStudent(AddCourseToStudentRequest request);
}
