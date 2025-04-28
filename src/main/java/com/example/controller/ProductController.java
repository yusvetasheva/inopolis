package com.example.controller;

import com.example.model.dto.ProductDTO;
import com.example.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/product")
public class ProductController {
    ProductService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Создать товар", description = "Доступ: только ADMIN",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Товар создан"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productDTO));
    }

    @GetMapping(value = "/get-all")
    @Operation(summary = "Получить все товары", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/get-by-id/{id}")
    @Operation(summary = "Получить товар по id", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет товара с таким идентификатором")
            })
    public ResponseEntity<ProductDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @Operation(summary = "Удалить товар по id", description = "Доступен для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар удален"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет товара с таким идентификатором")
            })
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Товар с id = " + id + " успешно удален");
    }

    @PutMapping(value = "update/{id}")
    @Operation(summary = "Обновить товар по id", description = "Доступен для ролей ADMIN, USER",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Товар обновлен"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет товара с таким идентификатором")
            })
    public ResponseEntity<ProductDTO> update(@PathVariable @NotNull Integer id, @RequestBody ProductDTO updated){
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
