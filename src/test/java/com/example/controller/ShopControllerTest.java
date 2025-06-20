package com.example.controller;

import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import com.example.security.SecurityConfig;
import com.example.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(ShopController.class)
@Import(SecurityConfig.class)
public class ShopControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShopService service;

    ShopEntity entity;
    ShopDTO dto;

    @BeforeEach
    public void initAll() {
        entity = ShopEntity.builder().shopName("name").isDeleted(false).build();
        dto = ShopDTO.builder().shopName("name").build();
    }


    @Test
    public void create_SuccessTest() throws Exception{
        when(service.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/shop/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46MTIz")
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).create(any());
    }

    @Test
    public void create_401Unauthorized() throws Exception{

        mockMvc.perform(post("/api/shop/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).create(any());

    }

    @Test
    public void getById_401Unauthorized() throws Exception{

        mockMvc.perform(get("/api/shop/get-by-id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).create(any());

    }

    @Test
    public void getById_Success() throws Exception{
        when(service.getById(any())).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/shop/get-by-id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic YWRtaW46MTIz")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(service, times(1)).getById(any());

    }

    @Test
    public void findAll_Success() throws Exception{

        // Данные для страницы
        List<ShopDTO> shopList = List.of(ShopDTO.builder().shopName("name1").build(), ShopDTO.builder().shopName("name2").build());
        Page<ShopDTO> shopPage = new PageImpl<>(shopList);

        when(service.findAll(any())).thenReturn(shopPage);

        mockMvc.perform(get("/api/shop/get-all")
                .param("page", "0")
                .param("size", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46MTIz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].shopName").value("name1"))
                .andExpect(jsonPath("$.content[1].shopName").value("name2"));

        verify(service, times(1)).findAll(any());
    }

    @Test
    public void findAll_401Unauthorized() throws Exception{

        mockMvc.perform(get("/api/shop/get-all")
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).findAll(any());
    }

    @Test
    public void deleteById_Success() throws Exception{
        doNothing().when(service).deleteById(any());

        mockMvc.perform(delete("/api/shop/delete-by-id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic YWRtaW46MTIz"))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(any());
    }

    @Test
    public void deleteById_401Unauthorized() throws Exception{

        mockMvc.perform(delete("/api/shop/delete-by-id/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).deleteById(any());
    }

    @Test
    public void update_SuccessTest() throws Exception{
        ShopDTO updated = ShopDTO.builder().shopName("new").build();

        when(service.update(any(), any())).thenReturn(updated);

        mockMvc.perform(put("/api/shop/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46MTIz")
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shopName").value("new"));

        verify(service, times(1)).update(any(), any());
    }

    @Test
    public void update_401Unauthorized() throws Exception{

        mockMvc.perform(put("/api/shop/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).update(any(), any());
    }
}
