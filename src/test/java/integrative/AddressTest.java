package integrative;

import com.example.App;
import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import com.example.repository.AddressRepository;
import com.example.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = App.class)
@AutoConfigureTestDatabase //Меняем Datasource на embedded H2
@Transactional //Каждому тесту своя транзакция
@Rollback // и откатываем её по завершении
public class AddressTest {

    @Autowired
    AddressRepository repository;

    @Autowired
    AddressService service;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void getById_SuccessTest() {
        AddressEntity newAddress = AddressEntity.builder()
                .street("street")
                .city("city")
                .numberOfBuild("1")
                .build();
        AddressEntity saved = repository.save(newAddress);

        Optional<AddressDTO> result = service.getById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getCity(), result.get().getCity());
        assertEquals(saved.getStreet(), result.get().getStreet());
        assertEquals(saved.getNumberOfBuild(), result.get().getNumberOfBuild());

        assertThat(repository.findAll(), hasSize(1));
    }

    @Test
    public void getById_noSuchElement() {
        assertThatThrownBy(() -> service.getById(1))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден адрес с id = 1");
    }

    @Test
    public void deleteById_SuccessTest() {
        AddressEntity saved = AddressEntity.builder().street("street").numberOfBuild("1").city("city").build();
        saved = repository.save(saved);

        service.deleteById(saved.getId());

        assertTrue(saved.getIsDeleted());
        assertThat(repository.findAll(), hasSize(1));
    }

    @Test
    public void deleteById_NoSuchElement() {
        assertThatThrownBy(() -> service.deleteById(999))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден адрес с id = 999")
        ;
    }

    @Test
    public void update_SuccessTest() {
        AddressDTO updated = AddressDTO.builder()
                .numberOfBuild("new build")
                .city("new city")
                .street("new street")
                .build();

        AddressEntity oldEntity = AddressEntity.builder()
                .city("old")
                .numberOfBuild("old")
                .street("old")
                .build();

        oldEntity = repository.save(oldEntity);

        AddressDTO result = service.update(oldEntity.getId(), updated);

        assertEquals("new city", result.getCity());
        assertEquals("new build", result.getNumberOfBuild());
        assertEquals("new street", result.getStreet());

        assertThat(repository.findAll(), hasSize(1));
    }

    @Test
    public void update_NoSuchElement() {
        assertThatThrownBy(() -> service.update(1, AddressDTO.builder().build()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден адрес с id = 1");
    }

    @Test
    public void findAll_SuccessTest() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            AddressDTO dto = new AddressDTO("C" + i, "S" + i, String.valueOf(i));
            service.create(dto);
        });

        Pageable pageable = PageRequest.of(0, 1, Sort.by("id"));

        Page<AddressDTO> result = service.findAll(pageable);

        assertThat(result.getTotalElements(), is(2L));
        assertThat(result.getTotalPages(), is(2));

        assertThat(result.getContent().get(0).getCity(), is("C1"));
        assertThat(result.getContent().get(0).getStreet(), is("S1"));
        assertThat(result.getContent().get(0).getNumberOfBuild(), is("1"));

    }

    @Test
    public void create_SuccessTest() {
        AddressDTO newAddress = AddressDTO.builder().street("street").city("city").numberOfBuild("1").build();

        AddressDTO result = service.create(newAddress);

        assertEquals("city", result.getCity());
        assertEquals("street", result.getStreet());
        assertEquals("1", result.getNumberOfBuild());

        assertThat(repository.findAll(), hasSize(1));
    }

}
