package com.example.repository;

import com.example.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Shop> getAllShops() {
        String sql = "SELECT * FROM shop";
        return jdbcTemplate.query(sql, new ShopRowMap());
    }

    @Override
    public void addShop(Shop shop) {
        String sql = "INSERT INTO shop (address_id, store_id, shop_name) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement st = con.prepareStatement(sql, new String[] {"id"});
            st.setInt(1, shop.getAddressId());
            st.setInt(2, shop.getStoreId());
            st.setString(3, shop.getShopName());
            return st;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            shop.setId(keyHolder.getKey().intValue());
        }
    }

    @Override
    public void updateShop(Shop shop) {
        String sql = "UPDATE shop SET address_id = ?, store_id = ?, shop_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, shop.getAddressId(), shop.getStoreId(), shop.getShopName(), shop.getId());
    }

    @Override
    public void deleteShop(int id) {
        String sql = "DELETE FROM shop where id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ShopRowMap implements RowMapper<Shop>{

        @Override
        public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Shop(
                    rs.getInt("id"),
                    rs.getInt("address_id"),
                    rs.getInt("store_id"),
                    rs.getString("shop_name"));
        }
    }
}
