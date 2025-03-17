package com.example.inopolis.controller;

import com.example.courses.model.AddCommentToCourseRequest;
import com.example.courses.model.dto.CourseDTO;
import com.example.inopolis.controller.security.SecurityTestConfig;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.service.CourseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
@Import(SecurityTestConfig.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addCourseToStudent_SuccessTest() throws Exception {

        when(service.addCourseToStudent(any())).thenReturn("Успех");

        mockMvc.perform(post("/api/student/add-course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(AddCourseToStudentRequest.builder().course("test").studentId(0).build())))
                .andExpect(status().isOk())
                .andExpect(content().string("Курс test успешно добавлен студенту с id = 0"));

        verify(service, times(1)).addCourseToStudent(any());

    }

    @Test
    public void addCourseToStudent_RepeatTest() throws Exception {

        when(service.addCourseToStudent(any())).thenReturn("Повтор");

        mockMvc.perform(post("/api/student/add-course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(AddCourseToStudentRequest.builder().course("test").studentId(0).build())))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент с id = 0 уже записан на курс test"));

        verify(service, times(1)).addCourseToStudent(any());

    }

    @Test
    public void addCourseToStudent_ErrorTest() throws Exception {

        when(service.addCourseToStudent(any())).thenReturn("Данный курс не активен");

        mockMvc.perform(post("/api/student/add-course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(AddCourseToStudentRequest.builder().course("test").studentId(0).build())))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Данный курс не активен"));

        verify(service, times(1)).addCourseToStudent(any());

    }

    @Test
    public void addCommentToCourse_Success() throws Exception {
        when(service.addCommentToCourse(any())).thenReturn(getCourse());

        mockMvc.perform(post("/api/student/add-comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                AddCommentToCourseRequest.builder()
                                        .courseName("test")
                                        .commentText("two")
                                        .build())))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.comments[0]").value("one"))
                .andExpect(jsonPath("$.comments[1]").value("two"))
                .andExpect(status().isOk());

        verify(service).addCommentToCourse(any());
    }

    public CourseDTO getCourse() {
        return CourseDTO.builder()
                .name("test")
                .comments(List.of("one", "two"))
                .isActive(true)
                .dateBegin(LocalDate.now())
                .build();
    }

}
