package com.example.mapper;

import com.example.model.dto.AddressDTO;
import com.example.model.dto.ProductDTO;
import com.example.model.dto.SellerDTO;
import com.example.model.dto.ShopDTO;
import com.example.model.dto.StoreDTO;
import com.example.model.entity.AddressEntity;
import com.example.model.entity.ProductEntity;
import com.example.model.entity.SellerEntity;
import com.example.model.entity.ShopEntity;
import com.example.model.entity.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-14T21:25:10+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class SellerMapperImpl implements SellerMapper {

    @Override
    public SellerDTO entityToDto(SellerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SellerDTO sellerDTO = new SellerDTO();

        sellerDTO.setFio( entity.getFio() );
        sellerDTO.setCurrPosition( entity.getCurrPosition() );
        sellerDTO.setShop( shopEntityToShopDTO( entity.getShop() ) );
        sellerDTO.setSalary( entity.getSalary() );

        return sellerDTO;
    }

    @Override
    public SellerEntity dtoToEntity(SellerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SellerEntity sellerEntity = new SellerEntity();

        sellerEntity.setFio( dto.getFio() );
        sellerEntity.setCurrPosition( dto.getCurrPosition() );
        sellerEntity.setShop( shopDTOToShopEntity( dto.getShop() ) );
        sellerEntity.setSalary( dto.getSalary() );

        return sellerEntity;
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

    protected ShopDTO shopEntityToShopDTO(ShopEntity shopEntity) {
        if ( shopEntity == null ) {
            return null;
        }

        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setShopName( shopEntity.getShopName() );
        shopDTO.setAddress( addressEntityToAddressDTO( shopEntity.getAddress() ) );
        shopDTO.setStore( storeEntityToStoreDTO( shopEntity.getStore() ) );

        return shopDTO;
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

    protected ShopEntity shopDTOToShopEntity(ShopDTO shopDTO) {
        if ( shopDTO == null ) {
            return null;
        }

        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setShopName( shopDTO.getShopName() );
        shopEntity.setAddress( addressDTOToAddressEntity( shopDTO.getAddress() ) );
        shopEntity.setStore( storeDTOToStoreEntity( shopDTO.getStore() ) );

        return shopEntity;
    }
}
