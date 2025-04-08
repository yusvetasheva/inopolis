package com.example.service;

import com.example.model.dto.ProductDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы с товарами
 */
public interface ProductService {
    /**
     * Получает товар по его идентификатору
     *
     * @param id идентификатор товара
     * @return Optional с товаром или пустой, если товар не найден
     */
    Optional<ProductDTO> getById(Integer id);


    /**
     * Удаляет товар по его идентификатору
     *
     * @param id идентификатор товара
     * @throws NoSuchElementException в случае, если товар с таким идентификатором не найден
     */
    void deleteById(Integer id);

    /**
     * Добавляет новый товар
     *
     * @param product добавляемый товар
     * @return ProductDTO добавленный товар
     */
    ProductDTO create(ProductDTO product);

    /**
     * Обновляет продукт по его идентификатору
     *
     * @param id             идентификатор товара
     * @param updatedProduct обновленный товар
     * @return ProductDTO обновленный товар
     * @throws NoSuchElementException в случае, если товар с таким идентификатором не найден
     */
    ProductDTO update(Integer id, ProductDTO updatedProduct);
}
