package com.example.service;

import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Сервис для работы с адресами.
 */
public interface AddressService {
    /**
     * Получает данные адреса по его идентификатору.
     *
     * @param id идентификатор адреса
     * @return Optional, содержащий AddressDTO, если продавец найден, или пустой Optional, если продавец не найден
     */
    Optional<AddressDTO> getById(Integer id);

    /**
     * Удаляет адрес из базы по его идентификатору
     *
     * @param id идентификатор адреса
     * @throws EntityNotFoundException если адрес с указанным id не найден (опционально)
     */
    void deleteById(Integer id);

    /**
     * Добавляет адрес в БД
     *
     * @param address добавляемый адрес
     * @return Данные созданного адреса
     */
    AddressDTO create(AddressDTO address);

    /**
     * Обновляет адрес по его идентификатору
     *
     * @param id             идентификатор адреса
     * @param updatedAddress обновленный адрес (допускается обновление части полей)
     * @return Обновленный адрес
     * @throws EntityNotFoundException если адрес с указанным id не найден (опционально)
     */
    AddressDTO update(Integer id, AddressDTO updatedAddress);

    /**
     * Получает данные всех адресов.
     *
     * @return Page, содержащий страницу AddressDTO
     */
    Page<AddressDTO> findAll(Pageable pageable);
}
