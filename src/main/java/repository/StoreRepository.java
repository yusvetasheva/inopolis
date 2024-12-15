package repository;

import model.Store;

import java.util.List;

public interface StoreRepository {
    List<Store> getAllStores();

    void addStore(Store store);

    void updateStore(Store store);

    void deleteStore(int id);
}
