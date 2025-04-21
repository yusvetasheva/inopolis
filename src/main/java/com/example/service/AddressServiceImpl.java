package com.example.service;

import com.example.mapper.AddressMapper;
import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import com.example.repository.AddressRepository;
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
public class AddressServiceImpl implements AddressService {

    AddressRepository repository;
    AddressMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<AddressDTO> getById(Integer id) {
        AddressEntity exist = findEntityById(id);
        return Optional.of(mapper.entityToDto(exist));
    }

    @Override
    public void deleteById(Integer id) {
        AddressEntity exist = findEntityById(id);
        exist.setIsDeleted(true);
        repository.save(exist);

    }

    @Override
    public AddressDTO create(AddressDTO address) {
        AddressEntity newAddress = repository.save(mapper.dtoToEntity(address));
        return mapper.entityToDto(newAddress);
    }

    @Override
    public AddressDTO update(Integer id, AddressDTO updatedAddress) {
        AddressEntity exist = findEntityById(id);

        if (updatedAddress.getCity() != null) exist.setCity(updatedAddress.getCity());
        if (updatedAddress.getStreet() != null) exist.setStreet(updatedAddress.getStreet());
        if (updatedAddress.getNumberOfBuild() != null) exist.setNumberOfBuild(updatedAddress.getNumberOfBuild());

        AddressEntity updated = repository.save(exist);
        return mapper.entityToDto(updated);
    }

    @Override
    public Page<AddressDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private AddressEntity findEntityById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Не найден адрес с id = " + id));
    }
}
