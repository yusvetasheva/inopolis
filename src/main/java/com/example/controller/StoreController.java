package com.example.controller;

import com.example.model.dto.StoreDTO;
import com.example.service.StoreService;
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
@RequestMapping(value = "/api/store")
@Tag(name = "Store Controller", description = "Контроллер для работы со складом")
public class StoreController {
    StoreService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Создать склад", description = "Доступно для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Склад создан"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав")
            })
    public ResponseEntity<StoreDTO> create(@RequestBody @Valid StoreDTO storeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(storeDTO));
    }

    @GetMapping(value = "/get-all")
    @Operation(summary = "Получить все склады", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<Page<StoreDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/get-by-id/{id}")
    @Operation(summary = "Получить склад по id", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет склада с таким идентификатором")
            })
    public ResponseEntity<StoreDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @Operation(summary = "Удалить склад по id", description = "Доступен для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Склад удален"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет склада с таким идентификатором")
            })
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Склад с id = " + id + " успешно удален");
    }

    @PutMapping(value = "update/{id}")
    @Operation(summary = "Обновить склад по id", description = "Доступен для ролей ADMIN, USER",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Склад обновлен"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет склада с таким идентификатором")
            })
    public ResponseEntity<StoreDTO> update(@PathVariable @NotNull Integer id, @RequestBody StoreDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }

}
