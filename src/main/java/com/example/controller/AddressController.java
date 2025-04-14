package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.service.AddressService;
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
 * Контроллер для управления адресами
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {
    AddressService service;

    /**
     * Создаёт новый экземпляр {@link AddressDTO}.
     *
     * @param addressDTO модель адреса
     */
    @PostMapping(value = "/create")
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(addressDTO));
    }

    /**
     * Получает список всех адресов {@link AddressDTO}.
     *
     * @param pageable модель с данными о номере и размене страницы. Нужно для пагинации
     */
    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<AddressDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    /**
     * Получает адрес по идентификатору {@link AddressDTO}.
     *
     * @param id идентификатор адреса
     */
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    /**
     * Удаляет адрес по идентификатору {@link AddressDTO}.
     *
     * @param id идентификатор адреса
     */
    @DeleteMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Адрес с id = " + id + " успешно удален");
    }

    /**
     * Обновляет адрес по идентификатору {@link AddressDTO}.
     *
     * @param id      идентификатор адреса
     * @param updated обновленные данные адреса
     */
    @PutMapping(value = "update/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable @NotNull Integer id, @RequestBody AddressDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
