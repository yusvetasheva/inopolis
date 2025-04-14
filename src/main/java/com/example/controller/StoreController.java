package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.StoreDTO;
import com.example.service.StoreService;
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
 * Контроллер для управления складами
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/store")
public class StoreController {
    StoreService service;

    /**
     * Создаёт новый экземпляр {@link StoreDTO}.
     *
     * @param storeDTO модель склада
     */
    @PostMapping(value = "/create")
    public ResponseEntity<StoreDTO> create(@RequestBody @Valid StoreDTO storeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(storeDTO));
    }

    /**
     * Получает список всех адресов {@link StoreDTO}.
     *
     * @param pageable модель с данными о номере и размене страницы. Нужно для пагинации
     */
    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<StoreDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    /**
     * Получает склад по идентификатору {@link StoreDTO}.
     *
     * @param id идентификатор склада
     */
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<StoreDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    /**
     * Удаляет товар по идентификатору {@link StoreDTO}.
     *
     * @param id идентификатор склада
     */
    @DeleteMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Склад с id = " + id + " успешно удален");
    }

    /**
     * Обновляет склад по идентификатору {@link StoreDTO}.
     *
     * @param id      идентификатор склада
     * @param updated обновленные данные склада
     */
    @PutMapping(value = "update/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable @NotNull Integer id, @RequestBody StoreDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }

}
