package com.example.inopolis.controller.security;

import com.example.inopolis.controller.LKController;
import com.example.inopolis.model.dto.StudentRegistrationDTO;
import com.example.inopolis.service.LKServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LKController.class)
@Import(SecurityTestConfig.class)
public class LKControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LKServiceImpl service;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getStudentCourses_Success() throws Exception {

        when(service.getStudentCourses(any())).thenReturn(List.of("course1", "course2"));

        mockMvc.perform(get("/api/lk/get-all-courses/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic " +
                                Base64.getEncoder().encodeToString("user:password".getBytes())))
                .andExpect(status().isOk());

        verify(service).getStudentCourses(any());
    }

    @Test
    public void registerStudent_BadRequest() throws Exception {
        when(service.registerStudent(any(), any())).thenReturn(ResponseEntity.ok().body("Студент успешно зарегистрирован"));

        mockMvc.perform(post("/api/lk/register-security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(StudentRegistrationDTO.builder().email("test").password("123").build())))
                .andExpect(status().isBadRequest());

        verify(service, times(0)).registerStudent(any(), any());

    }

    @Test
    public void registerStudent_Succes() throws Exception {
        when(service.registerStudent(any(), any())).thenReturn(ResponseEntity.ok().body("Студент успешно зарегистрирован"));

        mockMvc.perform(post("/api/lk/register-security")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(StudentRegistrationDTO.builder()
                                .email("svetashev200@gmail.com")
                                .password("123")
                                .build())))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент успешно зарегистрирован"));

        verify(service).registerStudent(any(), any());
    }

    @Test

    public void getStudentCourses_401() throws Exception {
        mockMvc.perform(get("/api/lk/get-all-courses/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("anonim:password".getBytes())))
                .andExpect(status().is4xxClientError());
    }
}
