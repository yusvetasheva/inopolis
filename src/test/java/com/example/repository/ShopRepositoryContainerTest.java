package com.example.repository;

import com.example.model.entity.ShopEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopRepositoryContainerTest extends ContainerPostgresTestConfiguration {

    @Autowired
    ShopRepository repository;

    @Test
    void CRUD_SuccessTest(){
        //create
        ShopEntity saved = repository.save(ShopEntity.builder().shopName("name").build());
        assertEquals(1, repository.findAll().size());

        //read
        Optional<ShopEntity> getEntity = repository.findById(saved.getId());

        assertTrue(getEntity.isPresent());
        assertEquals("name", getEntity.get().getShopName());

        //update
        getEntity.get().setShopName("new_shop");
        repository.save(getEntity.get());

        Optional<ShopEntity> updated = repository.findById(getEntity.get().getId());
        assertEquals("new_shop", getEntity.get().getShopName());

        //delete
        repository.deleteById(updated.get().getId());
        Optional<ShopEntity> afterDelete = repository.findById(updated.get().getId());
        assertTrue(afterDelete.isEmpty());
    }
}
