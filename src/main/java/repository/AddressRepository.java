package repository;

import model.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> getAllAddresses();
    void addAddress(Address address);
    Address getAddressById(int id);
    void updateAddress(Address address);
    void deleteAddress(int id);

}
