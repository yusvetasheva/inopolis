package com.example.inopolis.controller;

import com.example.inopolis.model.NoteDTO;
import com.example.inopolis.service.NoteService;
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
 * Аннотация @WebMvcTest(NoteController.class) поднимает контекст для тестирования
 * только веб-слоя, поэтому зависимости контроллера, такие как NoteService,
 * мокаются с помощью @MockBean.
 */
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateNote() throws Exception {
        NoteDTO dto = getDto();

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/notes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Заметка создана"));

        verify(noteService).create(any(NoteDTO.class));
    }

    @Test
    public void testGetByIdNote() throws Exception {
        NoteDTO noteDTO = getDto();

        when(noteService.getById(eq(3))).thenReturn(noteDTO);

        // Выполняем GET-запрос, используя переменную пути для id
        mockMvc.perform(get("/notes/get-by-id/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.topic").value("Тестовая тема"))
                .andExpect(jsonPath("$.fullText").value("Тестовый текст"));
    }

    @Test
    public void deleteByIdNote() throws Exception {

        mockMvc.perform(delete("/notes/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Зачетка удалена"));

    }

    @Test
    public void updateNote() throws Exception {

        NoteDTO noteDTO = getDto();

        mockMvc.perform(put("/notes/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Заявка обновлена"));
    }

    @Test
    public void getAll() throws Exception {
        NoteDTO noteDTO1 = getDto();
        NoteDTO noteDTO2 = getDto();

        when(noteService.getAllNotes()).thenReturn(List.of(noteDTO1, noteDTO2));

        mockMvc.perform(get("/notes/get-all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].topic").value(noteDTO1.getTopic()))
                .andExpect(jsonPath("$[1].fullText").value(noteDTO2.getFullText()));

    }

    public NoteDTO getDto(){
        return NoteDTO.builder()
                .id(3)
                .topic("Тестовая тема")
                .fullText("Тестовый текст")
                .build();
    }
}
