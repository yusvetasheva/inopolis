package com.example.inopolis.repository;

import com.example.inopolis.model.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SroreRepository extends JpaRepository<StoreEntity, Integer> {
    Page<StoreEntity> findAll(Pageable pageable);
}
