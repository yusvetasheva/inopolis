package repository;

import model.Seller;

import java.util.List;

public interface SellerRepository {
    List<Seller> getAllSellers();

    void addSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(int id);
}
