package com.example.repository;

import com.example.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностями {@link ProductEntity}.
 * Наследует базовую функциональность Spring Data JPA для операций CRUD.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
