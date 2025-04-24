package com.example.repository;

import com.example.model.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddressRepositoryContainerTest extends ContainerPostgresTestConfiguration {

    @Autowired
    AddressRepository repository;

    @Test
    void CRUD_SuccessTest() {
        //сохраняем
        AddressEntity saved = AddressEntity.builder().street("street").city("city").numberOfBuild("1").build();
        saved = repository.save(saved);

        //получаем
        Optional<AddressEntity> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("city", found.get().getCity());
        assertEquals("street", found.get().getStreet());
        assertEquals("1", found.get().getNumberOfBuild());
        assertFalse(found.get().getIsDeleted());

        //обновляем
        found.get().setStreet("new street");
        repository.save(found.get());

        Optional<AddressEntity> updated = repository.findById(found.get().getId());
        assertTrue(updated.isPresent());
        assertEquals("new street", updated.get().getStreet());

        // Удаляем
        repository.deleteById(saved.getId());
        Optional<AddressEntity> afterDelete = repository.findById(saved.getId());

        assertTrue(afterDelete.isEmpty());
    }



}
