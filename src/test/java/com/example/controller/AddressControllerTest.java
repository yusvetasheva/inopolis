package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import com.example.security.SecurityConfig;
import com.example.service.AddressService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AddressController.class)
@Import(SecurityConfig.class)
public class AddressControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AddressService service;

    private AddressEntity entity;
    private AddressDTO dto;

    @BeforeEach
    void setUp() {
        entity = AddressEntity.builder().city("city").street("street").numberOfBuild("1").build();
        dto = AddressDTO.builder().street("street").city("city").numberOfBuild("1").build();
    }

    @Test
    void create_SuccessTest() throws Exception {
        when(service.create(any())).thenReturn(dto);

        mockMvc.perform(post("/api/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        .header("Authorization", "Basic YWRtaW46MTIz")
                )
                .andExpect(status().isCreated());

        verify(service, times(1)).create(any());
    }

    @Test
    void create_BadRequestTest() throws Exception {

        dto.setCity(null);

        mockMvc.perform(post("/api/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        .header("Authorization", "Basic YWRtaW46MTIz")
                )
                .andExpect(status().isBadRequest());

        verify(service, times(0)).create(any());
    }

    @Test
    void create_NoAutorizedTest() throws Exception {

        mockMvc.perform(post("/api/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                )
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).create(any());
    }

    @Test
    void create_BadRoleTest() throws Exception {

        mockMvc.perform(post("/api/address/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                        .header("Authorization", "Basic YWRtaW46MTIzf")
                )
                .andExpect(status().isUnauthorized());

        verify(service, times(0)).create(any());
    }
}
