package repository;


import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO product (product_name, description, shop_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductName(), product.getDescription(), product.getShopId());
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE product SET product_name = ?, description = ?, shop_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getProductName(), product.getDescription(), product.getShopId(), product.getId());
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Product(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getInt("shopId"));
        }
    }
}
