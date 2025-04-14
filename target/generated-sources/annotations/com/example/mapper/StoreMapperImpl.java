package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ProductDTO;
import com.example.model.dto.StoreDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ProductEntity;
import com.example.model.entity.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-14T13:45:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDTO entityToDto(StoreEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setAddress( addressEntityToAddressDTO( entity.getAddress() ) );
        storeDTO.setCapacity( entity.getCapacity() );
        storeDTO.setFullness( entity.getFullness() );
        storeDTO.setProducts( productEntityListToProductDTOList( entity.getProducts() ) );

        return storeDTO;
    }

    @Override
    public StoreEntity dtoToEntity(StoreDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setAddress( addressDTOToAddressEntity( dto.getAddress() ) );
        storeEntity.setCapacity( dto.getCapacity() );
        storeEntity.setFullness( dto.getFullness() );
        storeEntity.setProducts( productDTOListToProductEntityList( dto.getProducts() ) );

        return storeEntity;
    }

    protected AddressDTO addressEntityToAddressDTO(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity( addressEntity.getCity() );
        addressDTO.setStreet( addressEntity.getStreet() );
        addressDTO.setNumberOfBuild( addressEntity.getNumberOfBuild() );

        return addressDTO;
    }

    protected ProductDTO productEntityToProductDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( productEntity.getName() );
        productDTO.setDescription( productEntity.getDescription() );

        return productDTO;
    }

    protected List<ProductDTO> productEntityListToProductDTOList(List<ProductEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( ProductEntity productEntity : list ) {
            list1.add( productEntityToProductDTO( productEntity ) );
        }

        return list1;
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

    protected ProductEntity productDTOToProductEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setName( productDTO.getName() );
        productEntity.setDescription( productDTO.getDescription() );

        return productEntity;
    }

    protected List<ProductEntity> productDTOListToProductEntityList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductEntity> list1 = new ArrayList<ProductEntity>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( productDTOToProductEntity( productDTO ) );
        }

        return list1;
    }
}
