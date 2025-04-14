package com.example.repository;

import com.example.model.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностями {@link SellerEntity}.
 * Наследует базовую функциональность Spring Data JPA для операций CRUD.
 */
@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {
}
