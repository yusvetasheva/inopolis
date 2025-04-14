package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/address")
@Tag(name = "Address Controller", description = "CRUD операции для адресов")
public class AddressController {
    AddressService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Создать адрес", description = "Доступ: только ADMIN",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Адрес создан"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(addressDTO));
    }

    @GetMapping(value = "/get-all")
    @Operation(summary = "Получить вес адреса", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<Page<AddressDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/get-by-id/{id}")
    @Operation(summary = "Получить адрес по id", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет адреса с таким идентификатором")
            })
    public ResponseEntity<AddressDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @Operation(summary = "Удалить адрес по id", description = "Доступен для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес удален"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет адреса с таким идентификатором")
            })
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Адрес с id = " + id + " успешно удален");
    }

    @PutMapping(value = "update/{id}")
    @Operation(summary = "Обновить адрес по id", description = "Доступен для ролей ADMIN, USER",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес обновлен"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет адреса с таким идентификатором")
            })
    public ResponseEntity<AddressDTO> update(@PathVariable @NotNull Integer id, @RequestBody AddressDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
