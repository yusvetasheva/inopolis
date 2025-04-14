package com.example.repository;

import com.example.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link AddressEntity}.
 * Наследует базовую функциональность Spring Data JPA для операций CRUD.
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
