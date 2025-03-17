package com.example.inopolis.service;

import com.example.courses.model.AddCommentToCourseRequest;
import com.example.courses.model.dto.CourseDTO;
import com.example.inopolis.model.AddCourseToStudentRequest;

public interface CourseService {

    String addCourseToStudent(AddCourseToStudentRequest request);

    CourseDTO addCommentToCourse(AddCommentToCourseRequest request);
}
