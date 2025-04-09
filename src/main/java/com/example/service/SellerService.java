package com.example.service;

import com.example.model.dto.ProductDTO;
import com.example.model.dto.SellerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Сервис для работы с продавцами
 */
public interface SellerService {

    /**
     * Возвращает данные о продавце по его идентификатору
     *
     * @param id идентификатор продавца
     * @return Optional с  SellerDTO
     * @throws NoSuchElementException в случае, если продавец с таким идентификатором не найден
     */

    Optional<SellerDTO> getById(Integer id);

    /**
     * Удаляет продавца по его идентификатору
     *
     * @param id идентификатор продавца
     * @throws NoSuchElementException в случае, если продавец с таким идентификатором не найден
     */

    void deleteById(Integer id);

    /**
     * Добавляет нового продавца в БД
     *
     * @param seller продавец для добавления в БД
     * @return SellerDTO добавленного продавца
     */

    SellerDTO create(SellerDTO seller);

    /**
     * Обновляет продавца по его идентификатору
     *
     * @param id            идентификатор продавца
     * @param updatedSeller обновленные данные продавца
     * @return SellerDTO обновленного продавца
     * @throws NoSuchElementException в случае, если продавец с таким идентификатором не найден
     */

    SellerDTO update(Integer id, SellerDTO updatedSeller);

    /**
     * Получает данные всех продавцов.
     *
     * @return Page, содержащий страницу SellerDTO
     */
    Page<SellerDTO> findAll(Pageable pageable);
}
