package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ShopDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ShopEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-21T13:52:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class ShopMapperImpl implements ShopMapper {

    @Override
    public ShopDTO entityToDto(ShopEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setShopName( entity.getShopName() );
        shopDTO.setAddress( addressEntityToAddressDTO( entity.getAddress() ) );

        return shopDTO;
    }

    @Override
    public ShopEntity dtoToEntity(ShopDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setShopName( dto.getShopName() );
        shopEntity.setAddress( addressDTOToAddressEntity( dto.getAddress() ) );

        return shopEntity;
    }

    protected AddressDTO addressEntityToAddressDTO(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.city( addressEntity.getCity() );
        addressDTO.street( addressEntity.getStreet() );
        addressDTO.numberOfBuild( addressEntity.getNumberOfBuild() );

        return addressDTO.build();
    }

    protected AddressEntity addressDTOToAddressEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setCity( addressDTO.getCity() );
        addressEntity.setStreet( addressDTO.getStreet() );
        addressEntity.setNumberOfBuild( addressDTO.getNumberOfBuild() );

        return addressEntity;
    }
}
