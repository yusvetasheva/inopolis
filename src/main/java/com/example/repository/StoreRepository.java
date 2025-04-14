package com.example.repository;

import com.example.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link StoreEntity}.
 * Наследует базовую функциональность Spring Data JPA для операций CRUD.
 */
@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
}
