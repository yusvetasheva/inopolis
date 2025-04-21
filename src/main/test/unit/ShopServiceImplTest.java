package unit;

import com.example.mapper.ShopMapper;
import com.example.model.dto.AddressDTO;
import com.example.model.dto.ShopDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ShopEntity;
import com.example.repository.ShopRepository;
import com.example.service.ShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopServiceImplTest {

    @Mock
    ShopRepository repository;

    @Mock
    ShopMapper mapper;

    @InjectMocks
    ShopServiceImpl service;

    private ShopEntity entity;
    private ShopDTO dto;

    @BeforeEach
    void setUp() {

        entity = ShopEntity.builder()
                .address(AddressEntity.builder()
                        .id(0)
                        .city("city")
                        .street("street")
                        .isDeleted(false)
                        .numberOfBuild("1")
                        .build())
                .shopName("shop")
                .isDeleted(false)
                .id(1)
                .build();

        dto = ShopDTO.builder()
                .address(AddressDTO.builder()
                        .city("city")
                        .street("street")
                        .numberOfBuild("1")
                        .build())
                .shopName("shop")
                .build();

    }

    @Test
    public void getById_SuccessTest() {
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(mapper.entityToDto(any())).thenReturn(dto);

        service.getById(1);

        verify(repository).findById(1);
        verify(mapper).entityToDto(entity);
    }

    @Test
    public void getById_NoSuchElementTest() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> service.getById(1));

        assertTrue(ex.getMessage().contains("Не найден магазин с id ="));
        verify(repository).findById(1);
    }

    @Test
    public void deleteById_SuccessTest() {
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        service.deleteById(1);

        assertTrue(entity.getIsDeleted());
        verify(repository).findById(1);
        verify(repository).save(entity);
    }

    @Test
    public void deleteById_NoSuchElementTest() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> service.deleteById(1));

        assertTrue(ex.getMessage().contains("Не найден магазин с id ="));
        verify(repository).findById(1);
    }

    @Test
    public void create_SuccessTest() {
        when(repository.save(any())).thenReturn(entity);
        when(mapper.entityToDto(any())).thenReturn(dto);
        when(mapper.dtoToEntity(dto)).thenReturn(entity);

        service.create(dto);

        verify(repository).save(entity);
        verify(mapper).dtoToEntity(dto);
        verify(mapper).entityToDto(entity);
    }

    @Test
    public void update_SuccessTest() {
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(mapper.entityToDto(any())).thenReturn(dto);
        when(repository.save(argThat(
                e -> e.getShopName().equals("new shop"))))
                .thenReturn(entity);

        ShopDTO updateDto = ShopDTO.builder().shopName("new shop").build();

        service.update(1, updateDto);

        verify(repository).findById(1);
        verify(mapper).entityToDto(entity);
    }

    @Test
    public void update_NoSuchElement(){
        when(repository.findById(any())).thenReturn(Optional.empty());

        ShopDTO updateDto = ShopDTO.builder().shopName("new shop").build();

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                ()->service.update(1, updateDto));

        assertTrue(ex.getMessage().contains("Не найден магазин с id ="));
        verify(repository).findById(1);
    }

    @Test
    public void findAll_SuccessTest(){
        Pageable pageable =  PageRequest.of(0, 1, Sort.by("id"));
        List<ShopEntity> entityList = List.of(entity);
        Page<ShopEntity> page =  new PageImpl<>(entityList, pageable, 0);

        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.entityToDto(entity)).thenReturn(dto);

        service.findAll(pageable);

        verify(repository).findAll(pageable);
        verify(mapper).entityToDto(entity);

    }

}
