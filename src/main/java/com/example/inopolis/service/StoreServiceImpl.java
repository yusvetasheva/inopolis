package com.example.inopolis.service;

import com.example.inopolis.mapper.StoreMapper;
import com.example.inopolis.model.dto.StoreDTO;
import com.example.inopolis.repository.SroreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreServiceImpl implements StoreService {

    SroreRepository repository;
    StoreMapper mapper;

    @Override
    public Page<StoreDTO> getAllStore(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public StoreDTO addStore(StoreDTO storeDTO) {
        repository.save(mapper.dtoToEntity(storeDTO));
        return storeDTO;
    }
}
