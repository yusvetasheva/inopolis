package com.example.inopolis.controller;

import com.example.courses.model.dto.CourseDTO;
import com.example.inopolis.controller.security.SecurityTestConfig;
import com.example.inopolis.model.AddCourseToStudentRequest;
import com.example.inopolis.model.dto.StudentDTO;
import com.example.inopolis.service.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Аннотация @WebMvcTest(StudentController.class) поднимает контекст для тестирования
 * только веб-слоя, поэтому зависимости контроллера, такие как NoteService,
 * мокаются с помощью @MockBean.
 */
@WebMvcTest(StudentController.class)
@Import(SecurityTestConfig.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getStudentWithSuchCoursesAmount_SuccessTest() throws Exception {

        List<StudentDTO> list = Collections.singletonList(getStudentDto());

        when(service.getStudentWithSuchCoursesAmount(2)).thenReturn(list);

        mockMvc.perform(get("/api/student/get-with-such-course-amount/{amount}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].fio").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$[0].email").value("petrov200@gmail.com"));

        verify(service, times(1)).getStudentWithSuchCoursesAmount(2);
    }

    @Test
    public void getStudentsWithCoursesLike_SuccessTest() throws Exception {
        List<StudentDTO> list = Collections.singletonList(getStudentDto());
        when(service.getStudentsWithCoursesLike("course")).thenReturn(list);

        mockMvc.perform(get("/api/student/get-course-like")
                        .param("courseName", "course")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].fio").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$[0].email").value("petrov200@gmail.com"));

        verify(service, times(1)).getStudentsWithCoursesLike("course");
    }

    @Test
    public void addStudent_SuccessTest() throws Exception {
        StudentDTO dto = getStudentDto();

        when(service.registerStudent(any(StudentDTO.class))).thenReturn(dto);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/student/add-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(service).registerStudent(any(StudentDTO.class));
    }

    @Test
    public void testGetAll() throws Exception {

        when(service.getAllStudents()).thenReturn(List.of(getStudentDto()));

        // Выполняем GET-запрос, используя переменную пути для id
        mockMvc.perform(get("/api/student/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].fio").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$[0].email").value("petrov200@gmail.com"));

        verify(service).getAllStudents();
    }

    @Test
    public void getStudentsByCourse() throws Exception {

        when(service.getStudentsByCourse(any())).thenReturn(List.of(getStudentDto()));

        mockMvc.perform(get("/api/student/get-student-by-course/{courseName}", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fio").value("Петров Петр Петрович"))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].email").value("petrov200@gmail.com"));

    }

    @Test
    public void deleteById() throws Exception {

        when(service.deleteStudent(any())).thenReturn(getStudentDto());

        mockMvc.perform(delete("/api/student/delete/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).deleteStudent(any());
    }

    @Test
    public void updateStudent() throws Exception {

        when(service.updateStudent(any(), any())).thenReturn(getStudentDto());

        mockMvc.perform(put("/api/student/update/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getStudentDto())))
                .andExpect(status().isOk());

        verify(service).updateStudent(any(), any());
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
                .name("test")
                .comments(Collections.singletonList("comment"))
                .build();
    }
}
