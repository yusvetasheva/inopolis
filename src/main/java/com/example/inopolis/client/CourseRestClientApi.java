package com.example.inopolis.client;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;

public interface CourseRestClientApi {
    CourseDTO checkCourseIsExist(String courseName);
}
