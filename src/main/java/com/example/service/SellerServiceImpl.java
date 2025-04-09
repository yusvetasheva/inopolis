package com.example.service;

import com.example.mapper.SellerMapper;
import com.example.mapper.ShopMapper;
import com.example.model.dto.SellerDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.SellerEntity;
import com.example.repository.SellerRepository;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class SellerServiceImpl implements SellerService {

    SellerRepository repository;
    SellerMapper mapper;
    ShopMapper shopMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<SellerDTO> getById(Integer id) {
        return Optional.of(mapper.entityToDto(findEntityById(id)));
    }

    @Override
    public void deleteById(Integer id) {
        SellerEntity exist = findEntityById(id);
        exist.setIsDeleted(true);
        repository.save(exist);
    }

    @Override
    public SellerDTO create(SellerDTO seller) {
        repository.save(mapper.dtoToEntity(seller));
        return seller;
    }

    @Override
    public SellerDTO update(Integer id, SellerDTO updatedSeller) {
        SellerEntity exist = findEntityById(id);

        if (updatedSeller.getFio() != null) exist.setFio(updatedSeller.getFio());
        if (updatedSeller.getShop() != null) exist.setShop(shopMapper.dtoToEntity(updatedSeller.getShop()));
        if (updatedSeller.getSalary() != null) exist.setSalary(updatedSeller.getSalary());
        if (updatedSeller.getCurrPosition() != null) exist.setCurrPosition(updatedSeller.getCurrPosition());

        SellerEntity updated = repository.save(exist);

        return mapper.entityToDto(updated);
    }

    @Override
    public Page<SellerDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private SellerEntity findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Seller с id  = " + id + " не найден"));
    }

}
