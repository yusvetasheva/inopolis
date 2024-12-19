package com.example.repository;

import com.example.model.Store;
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
public class StoreRepositoryImpl implements StoreRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Store> getAllStores() {
        String sql = "SELECT * FROM store";
        return jdbcTemplate.query(sql, new StoreRowMap());
    }

    @Override
    public void addStore(Store store) {
        String sql = "INSERT INTO store (address_id, capacity, fullness) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps =  con.prepareStatement(sql, new String[] {"id"});
            ps.setInt(1, store.getAddressId());
            ps.setInt(2, store.getCapacity());
            ps.setInt(3, store.getFullness());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            store.setId(keyHolder.getKey().intValue());
        }
    }

    @Override
    public void updateStore(Store store) {
        String sql = "UPDATE store SET address_id = ?, capacity = ?, fullness = ? WHERE id = ?";
        jdbcTemplate.update(sql, store.getAddressId(), store.getCapacity(), store.getFullness(), store.getId());
    }

    @Override
    public void deleteStore(int id) {
        String sql = "DELETE FROM store WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class StoreRowMap implements RowMapper<Store> {

        @Override
        public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Store(
                    rs.getInt("id"),
                    rs.getInt("address_id"),
                    rs.getInt("capacity"),
                    rs.getInt("fullness"));
        }
    }
}
