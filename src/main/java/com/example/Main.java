package com.example;

import com.example.config.JdbcTemplateConfig;
import com.example.model.Address;
import com.example.model.Product;
import com.example.model.Seller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import com.example.repository.AddressRepositoryImpl;
import com.example.repository.ProductRepositoryImpl;
import com.example.repository.SellerRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Good job");
    }

//        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplateConfig.class);
//
//        AddressRepositoryImpl addressRepository = context.getBean(AddressRepositoryImpl.class);
//        ProductRepositoryImpl productRepository = context.getBean(ProductRepositoryImpl.class);
//        SellerRepositoryImpl sellerRepository = context.getBean(SellerRepositoryImpl.class);
//
//
//        /**Адреса*/
//        // Получаем все адреса
//        List<Address> addresses = addressRepository.getAllAddresses();
//        for (Address address : addresses) {
//            System.out.println(address);
//        }
//
//        // Добавляем новый адрес
//        Address newAddress = new Address( "Moscow", "Tverskaya", "10");
//        addressRepository.addAddress(newAddress);
//
//        // Получаем адрес по ID
//        Address address = addressRepository.getAddressById(41);
//        System.out.println("Address with ID 41: " + address);
//
//        // Обновляем адрес
//        address.setCity("Saint Petersburg");
//        addressRepository.updateAddress(address);
//
//        // Удаляем адрес
//        addressRepository.deleteAddress(2);
//
//        /**Товары*/
//
//        // Добавляем новый адрес
//        Product newProduct = new Product("Matebook", "ochen dorogo", 21);
//        productRepository.addProduct(newProduct);
//
//        Product newProduct2 = new Product( "delete", "delete", 21);
//        productRepository.addProduct(newProduct);
//
//        // Обновляем адрес
//        newProduct.setDescription("ochen dorogo + 100");
//        productRepository.updateProduct(newProduct);
//
//        // Удаляем адрес
//        productRepository.deleteProduct(newProduct2.getId());
//
//        // Получаем все адреса
//        List<Product> products = productRepository.getAllProducts();
//        for (Product product : products) {
//            System.out.println(product);
//        }
//
//        /**Продавцы*/
//
//        // Добавляем новый адрес
//        Seller newSeller = new Seller("Oled AB", "seller", 42, 40000);
//        sellerRepository.addSeller(newSeller);
//
//        Seller newSeller2 = new Seller("delete", "delete", 42, 0);
//        sellerRepository.addSeller(newSeller2);
//
//        // Обновляем адрес
//        newSeller.setSalary(5000);
//        sellerRepository.updateSeller(newSeller);
//
//        // Удаляем адрес
//        sellerRepository.deleteSeller(newSeller2.getId());
//
//        // Получаем все адреса
//        List<Seller> sellers = sellerRepository.getAllSellers();
//        for (Seller seller : sellers) {
//            System.out.println(seller);
//        }
//    }
}
