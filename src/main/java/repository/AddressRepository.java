package repository;

import model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
@Repository
public class AddressRepository {

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    // Метод для получения всех адресов
    public List<Address> getAllAddresses() {
        String sql = "SELECT * FROM address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    // Метод для добавления нового адреса
    public void addAddress(Address address) {
        String sql = "INSERT INTO address (city, street, number_of_build) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, address.getCity(), address.getStreet(), address.getNumberOfBuild());
    }

    // Метод для получения адреса по ID
    public Address getAddressById(int id) {
        String sql = "SELECT * FROM address WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new AddressRowMapper());
    }

    // Метод для обновления адреса
    public void updateAddress(Address address) {
        String sql = "UPDATE address SET city = ?, street = ?, number_of_build = ? WHERE id = ?";
        jdbcTemplate.update(sql, address.getCity(), address.getStreet(), address.getNumberOfBuild(), address.getId());
    }

    // Метод для удаления адреса
    public void deleteAddress(int id) {
        String sql = "DELETE FROM address WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Вспомогательный класс для преобразования строки из ResultSet в объект Address
    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Address(rs.getInt("id"), rs.getString("city"), rs.getString("street"), rs.getString("number_of_build"));
        }
    }
}
