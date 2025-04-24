package unit;

import com.example.mapper.AddressMapper;
import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import com.example.repository.AddressRepository;
import com.example.service.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @Mock
    AddressRepository repository;

    @Mock
    AddressMapper mapper;

    @InjectMocks
    AddressServiceImpl service;

    private AddressEntity entity;
    private AddressDTO dto;

    @BeforeEach
    void setUp() {
        entity = new AddressEntity();
        entity.setId(1);
        entity.setCity("OldCity");
        entity.setStreet("OldStreet");
        entity.setNumberOfBuild("1A");
        entity.setIsDeleted(false);

        dto = new AddressDTO();
        dto.setCity("OldCity");
        dto.setStreet("OldStreet");
        dto.setNumberOfBuild("1A");
    }

    @Test
    public void deleteById_SuccessTest() {
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        service.deleteById(1);

        assertThat(entity.getIsDeleted(), is(true));
        verify(repository).findById(1);
        verify(repository).save(entity);
    }

    @Test
    public void deleteById_NotFoundTest() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> service.deleteById(1));

        assertThat(ex.getMessage(),containsString("Не найден адрес с id ="));
        verify(repository).findById(1);

    }

    @Test
    void getById_SuccessTest() {
        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(mapper.entityToDto(any())).thenReturn(dto);

        service.getById(1);

        verify(repository).findById(1);
        verify(mapper).entityToDto(entity);
    }

    @Test
    void getById_NoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> service.getById(1));

        assertThat(ex.getMessage(), containsString("Не найден адрес с id ="));
        verify(repository).findById(1);
    }

    @Test
    void create_SuccessTest() {
        when(repository.save(any())).thenReturn(entity);
        when(mapper.dtoToEntity(any())).thenReturn(entity);

        service.create(dto);

        verify(repository).save(entity);
        verify(mapper).dtoToEntity(dto);
    }

    @Test
    void update_SuccessTest() {
        AddressDTO updated = AddressDTO.builder()
                .city("new city")
                .numberOfBuild("new build")
                .street("new street")
                .build();

        when(repository.findById(any())).thenReturn(Optional.of(entity));
        when(repository.save(argThat(
                e ->
                        e.getCity().equals("new city") &&
                                e.getStreet().equals("new street") &&
                                e.getNumberOfBuild().equals("new build")
        ))).thenReturn(entity);

        when(mapper.entityToDto(any())).thenReturn(updated);

        AddressDTO result = service.update(1, updated);

        assertThat("new city", is(result.getCity()));
        assertThat("new street", is(result.getStreet()));
        assertThat("new build", is(result.getNumberOfBuild()));

        verify(repository).findById(1);
        verify(repository).save(any(AddressEntity.class));
        verify(mapper).entityToDto(any(AddressEntity.class));
    }

    @Test
    void update_NoSuchElementTest() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> service.update(1, dto));

        assertThat(ex.getMessage(), containsString("Не найден адрес с id ="));
        verify(repository).findById(1);
    }


    @Test
    void findAll_SuccessTest() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        List<AddressEntity> addressEntityList = List.of(entity);
        Page<AddressEntity> page = new PageImpl<>(addressEntityList, pageable, 1);

        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.entityToDto(entity)).thenReturn(dto);

        Page<AddressDTO> result = service.findAll(pageable);

        assertThat(result.getTotalElements(), is(1L));
        assertThat(dto, is(result.getContent().get(0)));

        verify(repository).findAll(pageable);
        verify(mapper).entityToDto(entity);

    }


}
