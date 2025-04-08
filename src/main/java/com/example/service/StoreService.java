package com.example.service;

import com.example.model.dto.StoreDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы со складами
 */
public interface StoreService {
    /**
     * Получает склад по его идентификатору
     *
     * @param id идентификатор склада
     * @return Optional со складом или пустой, если склад с таким идентификаторам не найден
     */
    Optional<StoreDTO> getById(Integer id);

    /**
     * Удаляет склад по его идентификатору
     *
     * @param id идентификатор склада
     * @throws NoSuchElementException в случае, если склад с таким идентификатор не найден
     */

    void deleteById(Integer id);


    /**
     * Добавляет новый склад
     *
     * @param shop новый склад
     * @return StoreDTO добавляемый склад
     */
    StoreDTO create(StoreDTO shop);


    /**
     * Обновляет склад по его идентификатору
     *
     * @param id           идентификатор склада
     * @param updatedStore обновленный склад
     * @return StoreDTO обновленный склад
     * @throws NoSuchElementException в случае, если склад с таким идентификаторам не найден
     */
    StoreDTO update(Integer id, StoreDTO updatedStore);
}
