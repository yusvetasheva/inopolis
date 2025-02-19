package com.example.inopolis.client;

import com.example.courses.dto.CourseDTO;

public interface CourseRestClientApi {
    CourseDTO checkCourseIsExist(String courseName);
}
