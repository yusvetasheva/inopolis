package com.example.service;

import com.example.model.dto.ShopDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы с магазином
 */
public interface ShopService {

    /**
     * Возвращает магазин по его идентификатору
     *
     * @param id идентификатор магазина
     * @return Optional с информацией о магазине
     * @throws NoSuchElementException в случае, если магазин с таким идентификатором не найден
     */
    Optional<ShopDTO> getById(Integer id);

    /**
     * Удаляет магазин по его идентификатору
     *
     * @param id идентификатор магазина
     * @throws NoSuchElementException в случае, если магазин с таким идентификатором не найден
     */
    void deleteById(Integer id);

    /**
     * Добавляет новый магазин в БД
     *
     * @param shop иновый магазин
     * @return ShopDTO с информацией о новом магазине
     */
    ShopDTO create(ShopDTO shop);

    /**
     * Обновляет магазин по его идентификатору
     *
     * @param id          идентификатор магазина
     * @param updatedShop обновленные данные магазина
     * @return ShopDTO с обновленной информацией о магазине
     * @throws NoSuchElementException в случае, если магазин с таким идентификатором не найден
     */
    ShopDTO update(Integer id, ShopDTO updatedShop);

    /**
     * Получает данные всех магазинов.
     *
     * @return Page, содержащий страницу ShopDTO
     */
    Page<ShopDTO> findAll(Pageable pageable);
}
