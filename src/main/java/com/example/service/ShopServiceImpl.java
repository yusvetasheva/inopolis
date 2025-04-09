package com.example.service;

import com.example.mapper.AddressMapper;
import com.example.mapper.ShopMapper;
import com.example.mapper.StoreMapper;
import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import com.example.repository.ShopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class ShopServiceImpl implements ShopService {

    ShopRepository repository;
    ShopMapper mapper;
    StoreMapper storeMapper;
    AddressMapper addressMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<ShopDTO> getById(Integer id) {
        return Optional.of(mapper.entityToDto(getEntityById(id)));
    }

    @Override
    public void deleteById(Integer id) {
        getEntityById(id);
        repository.deleteById(id);
    }

    @Override
    public ShopDTO create(ShopDTO seller) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(seller)));
    }

    @Override
    public ShopDTO update(Integer id, ShopDTO updatedShop) {
        ShopEntity exist = getEntityById(id);

        if (updatedShop.getShopName()!= null) exist.setShopName(updatedShop.getShopName());
        if (updatedShop.getStore()!= null) exist.setStore(storeMapper.dtoToEntity(updatedShop.getStore()));
        if (updatedShop.getAddress()!= null) exist.setAddress(addressMapper.dtoToEntity(updatedShop.getAddress()));

        ShopEntity updated = repository.save(exist);

        return mapper.entityToDto(updated);
    }

    private ShopEntity getEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shop с id = " + id + " не найден"));
    }
}
