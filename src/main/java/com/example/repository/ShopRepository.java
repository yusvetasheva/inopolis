package com.example.repository;

import com.example.model.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link ShopEntity}.
 * Наследует базовую функциональность Spring Data JPA для операций CRUD.
 */
@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {
}
