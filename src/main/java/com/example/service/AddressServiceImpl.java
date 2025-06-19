package com.example.service;

import com.example.mapper.AddressMapper;
import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import com.example.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "address", key = "#id")
    public Optional<AddressDTO> getById(Integer id) {
        AddressEntity exist = findEntityById(id);
        rabbitTemplate.convertAndSend("events_queue", "AddressServiceImpl.getById был вызван для id = " + id);
        return Optional.of(mapper.entityToDto(exist));
    }

    @Override
    @CacheEvict(value = "address", key = "#id")
    public void deleteById(Integer id) {
        AddressEntity exist = findEntityById(id);
        exist.setIsDeleted(true);
        repository.save(exist);
        rabbitTemplate.convertAndSend("events_queue", "AddressServiceImpl.deleteById был вызван для id = " + id);

    }

    @Override
    @CachePut(value = "address", key = "#result.id")
    public AddressDTO create(AddressDTO address) {
        AddressEntity newAddress = repository.save(mapper.dtoToEntity(address));
        rabbitTemplate.convertAndSend("events_queue", "AddressServiceImpl.create был вызван для id = " + newAddress.getId());
        return mapper.entityToDto(newAddress);
    }

    @Override
    @CachePut(value = "address", key = "#id") // Обновим кэш
    public AddressDTO update(Integer id, AddressDTO updatedAddress) {
        AddressEntity exist = findEntityById(id);

        if (updatedAddress.getCity() != null) exist.setCity(updatedAddress.getCity());
        if (updatedAddress.getStreet() != null) exist.setStreet(updatedAddress.getStreet());
        if (updatedAddress.getNumberOfBuild() != null) exist.setNumberOfBuild(updatedAddress.getNumberOfBuild());

        AddressEntity updated = repository.save(exist);
        rabbitTemplate.convertAndSend("events_queue", "AddressServiceImpl.create был вызван для id = " + id);
        return mapper.entityToDto(updated);
    }

    @Override
    @Cacheable(value = "address", key = "T(String).valueOf(#pageable.pageNumber) + '-' + T(String).valueOf(#pageable.pageSize) + '-' + #pageable.sort.toString()")
    public Page<AddressDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private AddressEntity findEntityById(Integer id) {
        rabbitTemplate.convertAndSend("events_queue", "AddressServiceImpl.findEntityById был вызван для id = " + id);
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Не найден адрес с id = " + id));
    }
}
