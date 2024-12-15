package repository;

import model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
@Repository
public class AddressRepositoryImpl implements AddressRepository{

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    public List<Address> getAllAddresses() {
        String sql = "SELECT * FROM address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    public void addAddress(Address address) {
        String sql = "INSERT INTO address (city, street, number_of_build) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, address.getCity(), address.getStreet(), address.getNumberOfBuild());
    }

    public Address getAddressById(int id) {
        String sql = "SELECT * FROM address WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AddressRowMapper());
    }

    public void updateAddress(Address address) {
        String sql = "UPDATE address SET city = ?, street = ?, number_of_build = ? WHERE id = ?";
        jdbcTemplate.update(sql, address.getCity(), address.getStreet(), address.getNumberOfBuild(), address.getId());
    }

    public void deleteAddress(int id) {
        String sql = "DELETE FROM address WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Address(rs.getInt("id"), rs.getString("city"), rs.getString("street"), rs.getString("number_of_build"));
        }
    }
}
