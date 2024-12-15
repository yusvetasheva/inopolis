package repository;

import model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SellerRepositoryImpl implements SellerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Seller> getAllSellers() {
        String sql = "SELECT * FROM seller";
        return jdbcTemplate.query(sql, new SellerRowMapper());
    }

    @Override
    public void addSeller(Seller seller) {
        String sql = "INSERT INTO seller (fio, cust_position, store_id, salary) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, seller.getFio(), seller.getCustPosition(), seller.getStoreId(), seller.getSalary());
    }

    @Override
    public void updateSeller(Seller seller) {
        String sql = "UPDATE seller SET fio = ?, cust_position = ?, store_id = ?, salary = ? WHERE id = ?";
        jdbcTemplate.update(sql, seller.getFio(), seller.getCustPosition(), seller.getStoreId(), seller.getSalary(), seller.getId());
    }

    @Override
    public void deleteSeller(int id) {
        String sql = "DELETE FROM seller where id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class SellerRowMapper implements org.springframework.jdbc.core.RowMapper<Seller> {
        @Override
        public Seller mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Seller(
                    rs.getInt("id"),
                    rs.getString("fio"),
                    rs.getString("cust_position"),
                    rs.getInt("store_id"),
                    rs.getInt("salary"));
        }
    }
}
