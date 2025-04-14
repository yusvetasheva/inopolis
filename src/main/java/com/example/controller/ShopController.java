package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ShopDTO;
import com.example.service.ShopService;
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

/**
 * Контроллер для управления магазинами
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/shop")
public class ShopController {
    ShopService service;

    /**
     * Создаёт новый экземпляр {@link ShopDTO}.
     *
     * @param shopDTO модель магазина
     */
    @PostMapping(value = "/create")
    public ResponseEntity<ShopDTO> create(@RequestBody @Valid ShopDTO shopDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(shopDTO));
    }

    /**
     * Получает список всех магазинов {@link ShopDTO}.
     *
     * @param pageable модель с данными о номере и размене страницы. Нужно для пагинации
     */

    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<ShopDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    /**
     * Получает магазин по идентификатору {@link ShopDTO}.
     *
     * @param id идентификатор магазина
     */

    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<ShopDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }
    /**
     * Удаляет магазин по идентификатору {@link ShopDTO}.
     *
     * @param id идентификатор магазина
     */

    @DeleteMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Магазин с id = " + id + " успешно удален");
    }
    /**
     * Обновляет магазин по идентификатору {@link ShopDTO}.
     *
     * @param id      идентификатор магазина
     * @param updated обновленные данные магазина
     */
    @PutMapping(value = "update/{id}")
    public ResponseEntity<ShopDTO> update(@PathVariable @NotNull Integer id, @RequestBody ShopDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
