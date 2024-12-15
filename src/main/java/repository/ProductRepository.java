package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);
}
