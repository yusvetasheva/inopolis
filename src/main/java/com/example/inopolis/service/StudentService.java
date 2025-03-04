package com.example.inopolis.service;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    List<StudentDTO> getStudentsByCourse(String courseName);

    List<StudentDTO> getStudentWithSuchCoursesAmount(int amount);

    List<StudentDTO> getStudentsWithCoursesLike(String course);

    StudentDTO registerStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Integer id, StudentDTO student);

    StudentDTO deleteStudent(Integer id);

    String addCourseToStudent(AddCourseToStudentRequest request);

    CourseDTO addCommentToCourse(AddCommentToCourseRequest request);

}
