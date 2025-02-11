package com.example.inopolis.controller;

import com.example.inopolis.mapper.CourseMapper;
import com.example.inopolis.mapper.StudentMapper;
import com.example.inopolis.model.CourseDTO;
import com.example.inopolis.model.CourseEnum;
import com.example.inopolis.model.StudentDTO;
import com.example.inopolis.service.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Аннотация @WebMvcTest(StudentController.class) поднимает контекст для тестирования
 * только веб-слоя, поэтому зависимости контроллера, такие как NoteService,
 * мокаются с помощью @MockBean.
 */
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddStudent() throws Exception {
        StudentDTO dto = getStudentDto();

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/student/add-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент успешно добавлен"));

        verify(service).addStudent(any(StudentDTO.class));
    }

    @Test
    public void testGetAll() throws Exception {

        when(service.getAllStudents()).thenReturn(List.of(getStudentDto()));

        // Выполняем GET-запрос, используя переменную пути для id
        mockMvc.perform(get("/api/student/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].fio").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$[0].email").value("petrov200@gmail.com"));
    }

    @Test
    public void testAddCourseToStudent() throws Exception {

        mockMvc.perform(post("/api/student/add-course/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCourseDto())))
                .andExpect(status().isOk())
                .andExpect(content().string("Курс успешно добавлен"));

        verify(service).addCourseToStudent(any(Integer.class), any(CourseDTO.class));

    }

    @Test
    public void deleteById() throws Exception {

        mockMvc.perform(delete("/api/student/delete/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент успешно удален"));

    }

    @Test
    public void updateStudent() throws Exception {

        mockMvc.perform(put("/api/student/update/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getStudentDto())))
                .andExpect(status().isOk())
                .andExpect(content().string("Студент успешно обновлен"));
    }

    public StudentDTO getStudentDto() {
        return StudentDTO.builder()
                .id(3)
                .fio("Петров Петр Петрович")
                .email("petrov200@gmail.com")
                .build();
    }

    public CourseDTO getCourseDto() {
        return CourseDTO.builder()
                .course(CourseEnum.MATH)
                .build();
    }
}
