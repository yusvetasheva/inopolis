package com.example.service;

import com.example.mapper.AddressMapper;
import com.example.mapper.ShopMapper;
import com.example.model.dto.ShopDTO;
import com.example.model.entity.ShopEntity;
import com.example.repository.ShopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
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
    AddressMapper addressMapper;
    KafkaTemplate<String, String> kafkaTemplate;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "shop", key = "#id")
    public Optional<ShopDTO> getById(Integer id) {
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.getById был вызван для id = " + id);
        return Optional.of(mapper.entityToDto(getEntityById(id)));
    }

    @Override
    @CacheEvict(value = "shop", key = "#id")
    public void deleteById(Integer id) {
        ShopEntity exist = getEntityById(id);
        exist.setIsDeleted(true);
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.deleteById был вызван для id = " + id);
        repository.save(exist);
    }

    @Override
    @CachePut(value = "shop", key = "#result.id")
    public ShopDTO create(ShopDTO seller) {
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.create был вызван");
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(seller)));
    }

    @Override
    @CachePut(value = "shop", key = "#id")
    public ShopDTO update(Integer id, ShopDTO updatedShop) {
        ShopEntity exist = getEntityById(id);

        if (updatedShop.getShopName()!= null) exist.setShopName(updatedShop.getShopName());
        if (updatedShop.getAddress()!= null) exist.setAddress(addressMapper.dtoToEntity(updatedShop.getAddress()));

        ShopEntity updated = repository.save(exist);
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.update был вызван для id = " + id);

        return mapper.entityToDto(updated);
    }

    @Override
    @Cacheable(value = "shop", key = "T(String).valueOf(#pageable.pageNumber) + '-' + T(String).valueOf(#pageable.pageSize) + '-' + #pageable.sort.toString()")
    public Page<ShopDTO> findAll(Pageable pageable) {
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.findAll был вызван");
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    private ShopEntity getEntityById(Integer id) {
        kafkaTemplate.send("method-calls-topic", "ShopServiceImpl.getEntityById был вызван для id = " + id);
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Не найден магазин с id = " + id));
    }
}
