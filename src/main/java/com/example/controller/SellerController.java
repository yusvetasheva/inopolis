package com.example.controller;

import com.example.model.dto.SellerDTO;
import com.example.service.SellerService;
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
@RequestMapping(value = "/api/seller")
@Tag(name = "SellerController", description = "Контроллер для работы с продавцами")
public class SellerController {
    SellerService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Создать продавца", description = "Доступ: только ADMIN",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Продавец создан"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<SellerDTO> create(@RequestBody @Valid SellerDTO sellerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(sellerDTO));
    }

    @GetMapping(value = "/get-all")
    @Operation(summary = "Получить всех продавцов", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<Page<SellerDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/get-by-id/{id}")
    @Operation(summary = "Получить продавца по id", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет продавца с таким идентификатором")
            })
    public ResponseEntity<SellerDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @Operation(summary = "Удалить продавца по id", description = "Доступен для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес удален"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет продавца с таким идентификатором")
            })
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Продавец с id = " + id + " успешно удален");
    }

    @PutMapping(value = "update/{id}")
    @Operation(summary = "Обновить продавца по id", description = "Доступен для ролей ADMIN, USER",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес обновлен"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет продавца с таким идентификатором")
            })
    public ResponseEntity<SellerDTO> update(@PathVariable @NotNull Integer id, @RequestBody SellerDTO updated){
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
