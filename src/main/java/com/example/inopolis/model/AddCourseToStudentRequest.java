package com.example.inopolis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    Integer studentId;
    @JsonProperty(value = "course")
    @NotBlank
    String course;
}
