package com.example.repository;

import com.example.model.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
public class AddressRepositoryContainerTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void registerPgProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

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
