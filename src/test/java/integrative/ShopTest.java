package integrative;

import com.example.App;
import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import com.example.repository.ShopRepository;
import com.example.service.ShopService;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = App.class)
@AutoConfigureTestDatabase //Меняем Datasource на embedded H2
@Transactional //Каждому тесту своя транзакция
@Rollback // и откатываем её по завершении
public class ShopTest {

    @Autowired
    ShopRepository repository;

    @Autowired
    ShopService service;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void getById_SuccessTest() {
        ShopEntity newShop = ShopEntity.builder()
                .shopName("name")
                .build();
        ShopEntity saved = repository.save(newShop);

        Optional<ShopDTO> result = service.getById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getShopName(), result.get().getShopName());
        assertThat(repository.findAll(), hasSize(1));
    }

    @Test
    public void getById_noSuchElement() {
        assertThatThrownBy(() -> service.getById(1))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден магазин с id = 1");
    }

    @Test
    public void deleteById_SuccessTest() {
        ShopEntity saved = ShopEntity.builder().shopName("name").build();
        saved = repository.save(saved);

        service.deleteById(saved.getId());

        assertThat(saved.getIsDeleted(), is(true));
        assertThat(repository.findAll(), hasSize(1));
    }

    @Test
    public void deleteById_NoSuchElement() {
        assertThatThrownBy(() -> service.deleteById(999))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден магазин с id = 999")
        ;
    }


    @Test
    public void update_NoSuchElement() {
        assertThatThrownBy(() -> service.update(1, ShopDTO.builder().build()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Не найден магазин с id = 1");
    }

    @Test
    public void findAll_SuccessTest() {
        IntStream.rangeClosed(1, 2).forEach(i -> {
            ShopDTO dto = ShopDTO.builder().shopName("name").build();
            service.create(dto);
        });

        Pageable pageable = PageRequest.of(0, 1, Sort.by("id"));

        Page<ShopDTO> result = service.findAll(pageable);

        assertThat(result.getTotalElements(), is(2L));
        assertThat(result.getTotalPages(), is(2));

        assertThat(result.getContent().get(0).getShopName(), is("name"));

    }

    @Test
    public void create_SuccessTest() {
        ShopDTO newShop = ShopDTO.builder().shopName("name").build();

        ShopDTO result = service.create(newShop);

        assertEquals("name", result.getShopName());

        assertThat(repository.findAll(), hasSize(1));
    }

}
