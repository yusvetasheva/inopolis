package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.SellerDTO;
import com.example.service.SellerService;
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
 * Контроллер для управления данными продавцов
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/seller")
public class SellerController {
    SellerService service;

    /**
     * Создаёт новый экземпляр {@link SellerDTO}.
     *
     * @param sellerDTO модель продавца
     */
    @PostMapping(value = "/create")
    public ResponseEntity<SellerDTO> create(@RequestBody @Valid SellerDTO sellerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(sellerDTO));
    }

    /**
     * Получает список всех продавцов {@link SellerDTO}.
     *
     * @param pageable модель с данными о номере и размене страницы. Нужно для пагинации
     */
    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<SellerDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    /**
     * Получает продавца по идентификатору {@link SellerDTO}.
     *
     * @param id идентификатор продавца
     */
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<SellerDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    /**
     * Удаляет продавца по идентификатору {@link SellerDTO}.
     *
     * @param id идентификатор продавца
     */
    @DeleteMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Продавец с id = " + id + " успешно удален");
    }

    /**
     * Обновляет продавца по идентификатору {@link SellerDTO}.
     *
     * @param id      идентификатор продавца
     * @param updated обновленные данные продавца
     */
    @PutMapping(value = "update/{id}")
    public ResponseEntity<SellerDTO> update(@PathVariable @NotNull Integer id, @RequestBody SellerDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
