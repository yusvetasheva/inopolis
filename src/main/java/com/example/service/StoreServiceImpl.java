package com.example.service;

import com.example.mapper.AddressMapper;
import com.example.mapper.StoreMapper;
import com.example.model.dto.StoreDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.StoreEntity;
import com.example.repository.StoreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    StoreRepository repository;
    StoreMapper mapper;
    AddressMapper addressMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<StoreDTO> getById(Integer id) {
        return Optional.of(mapper.entityToDto(getEntityById(id)));
    }

    @Override
    public void deleteById(Integer id) {
        StoreEntity exist = getEntityById(id);
        exist.setIsDeleted(true);
        repository.save(exist);
    }

    @Override
    public StoreDTO create(StoreDTO shop) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(shop)));
    }

    @Override
    public StoreDTO update(Integer id, StoreDTO updatedStore) {
        StoreEntity exist = getEntityById(id);

        if (updatedStore.getCapacity() != null) exist.setCapacity(updatedStore.getCapacity());
        if (updatedStore.getFullness() != null) exist.setFullness(updatedStore.getFullness());
        if (updatedStore.getAddress() != null) exist.setAddress(addressMapper.dtoToEntity(updatedStore.getAddress()));


        StoreEntity updated = repository.save(exist);
        return mapper.entityToDto(updated);
    }

    @Override
    public Page<StoreDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private StoreEntity getEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Store с id = " + id + " не найден"));
    }
}
