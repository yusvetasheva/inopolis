package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.entity.AddressEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-21T13:52:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO entityToDto(AddressEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.city( entity.getCity() );
        addressDTO.street( entity.getStreet() );
        addressDTO.numberOfBuild( entity.getNumberOfBuild() );

        return addressDTO.build();
    }

    @Override
    public AddressEntity dtoToEntity(AddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setCity( dto.getCity() );
        addressEntity.setStreet( dto.getStreet() );
        addressEntity.setNumberOfBuild( dto.getNumberOfBuild() );

        return addressEntity;
    }
}
