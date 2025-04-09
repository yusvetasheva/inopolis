package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ProductDTO;
import com.example.model.dto.ShopDTO;
import com.example.model.dto.StoreDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ProductEntity;
import com.example.model.entity.ShopEntity;
import com.example.model.entity.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T16:49:45+0300",
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
        shopDTO.setStore( storeEntityToStoreDTO( entity.getStore() ) );

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
        shopEntity.setStore( storeDTOToStoreEntity( dto.getStore() ) );

        return shopEntity;
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

    protected StoreDTO storeEntityToStoreDTO(StoreEntity storeEntity) {
        if ( storeEntity == null ) {
            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setAddress( addressEntityToAddressDTO( storeEntity.getAddress() ) );
        storeDTO.setCapacity( storeEntity.getCapacity() );
        storeDTO.setFullness( storeEntity.getFullness() );
        storeDTO.setProducts( productEntityListToProductDTOList( storeEntity.getProducts() ) );

        return storeDTO;
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

    protected StoreEntity storeDTOToStoreEntity(StoreDTO storeDTO) {
        if ( storeDTO == null ) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setAddress( addressDTOToAddressEntity( storeDTO.getAddress() ) );
        storeEntity.setCapacity( storeDTO.getCapacity() );
        storeEntity.setFullness( storeDTO.getFullness() );
        storeEntity.setProducts( productDTOListToProductEntityList( storeDTO.getProducts() ) );

        return storeEntity;
    }
}
