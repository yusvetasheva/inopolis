package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class AddCourseToStudentRequest {
    @JsonProperty(value = "studentId")
    Integer studentId;
    @JsonProperty(value = "course")
    String course;
}
