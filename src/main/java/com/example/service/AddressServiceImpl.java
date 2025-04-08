package com.example.service;

import com.example.model.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{
    @Override
    public Optional<AddressDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public AddressDTO create(AddressDTO address) {
        return null;
    }

    @Override
    public AddressDTO update(Integer id, AddressDTO updatedAddress) {
        return null;
    }
}
