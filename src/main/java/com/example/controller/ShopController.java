package com.example.controller;

import com.example.model.dto.SellerDTO;
import com.example.model.dto.ShopDTO;
import com.example.service.ShopService;
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
@RequestMapping(value = "/api/shop")
@Tag(name = "ShopController", description = "Контроллер для работы с магазинами")
public class ShopController {
    ShopService service;

    @PostMapping(value = "/create")
    @Operation(summary = "Создать магазин", description = "Доступ: только ADMIN",
            responses = {
                    @ApiResponse(responseCode = "201", description = "магазин создан"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<ShopDTO> create(@RequestBody @Valid ShopDTO shopDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(shopDTO));
    }

    @GetMapping(value = "/get-all")
    @Operation(summary = "Получить все магазины", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Нет прав")
            })
    public ResponseEntity<Page<ShopDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/get-by-id/{id}")
    @Operation(summary = "Получить магазин по id", description = "Доступен для всех ролей (admin. user, viewer)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные получены"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет магазина с таким идентификатором")
            })
    public ResponseEntity<ShopDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @Operation(summary = "Удалить магазин по id", description = "Доступен для роли ADMIN",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес удален"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет магазина с таким идентификатором")
            })
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Магазин с id = " + id + " успешно удален");
    }

    @PutMapping(value = "update/{id}")
    @Operation(summary = "Обновить магазин по id", description = "Доступен для ролей ADMIN, USER",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Адрес обновлен"),
                    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
                    @ApiResponse(responseCode = "404", description = "Нет магазина с таким идентификатором")
            })
    public ResponseEntity<ShopDTO> update(@PathVariable @NotNull Integer id, @RequestBody ShopDTO updated){
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
