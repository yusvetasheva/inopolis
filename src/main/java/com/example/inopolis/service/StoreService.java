package com.example.inopolis.service;

import com.example.inopolis.model.dto.StoreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {
    Page<StoreDTO> getAllStore(Pageable pageable);

    StoreDTO addStore(StoreDTO storeDTO);
}
