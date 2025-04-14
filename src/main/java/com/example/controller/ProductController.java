package com.example.controller;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ProductDTO;
import com.example.service.ProductService;
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
 * Контроллер для управления товарами
 */
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    ProductService service;

    /**
     * Создаёт новый экземпляр {@link ProductDTO}.
     *
     * @param productDTO модель товара
     */
    @PostMapping(value = "/create")
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productDTO));
    }

    /**
     * Получает список всех товаров {@link ProductDTO}.
     *
     * @param pageable модель с данными о номере и размене страницы. Нужно для пагинации
     */
    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    /**
     * Получает njdfh по идентификатору {@link ProductDTO}.
     *
     * @param id идентификатор товара
     */
    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable @NotNull Integer id) {
        return ResponseEntity.ok().body(service.getById(id).get());
    }

    /**
     * Удаляет товар по идентификатору {@link ProductDTO}.
     *
     * @param id идентификатор товара
     */
    @DeleteMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable @NotNull Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok().body("Товар с id = " + id + " успешно удален");
    }

    /**
     * Обновляет адрес по идентификатору {@link ProductDTO}.
     *
     * @param id      идентификатор товара
     * @param updated обновленные данные товара
     */

    @PutMapping(value = "update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable @NotNull Integer id, @RequestBody ProductDTO updated) {
        return ResponseEntity.ok().body(service.update(id, updated));
    }
}
