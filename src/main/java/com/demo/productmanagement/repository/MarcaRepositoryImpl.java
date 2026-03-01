package com.demo.productmanagement.repository;

import com.demo.productmanagement.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@Repository
public class MarcaRepositoryImpl implements  MarcaRepository{

    private final JdbcTemplate jdbcTemplate;

    public MarcaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Marca guardar(Marca marca) {
        String sql = "INSERT INTO marcas (nombre,  precio ) VALUES (?,  ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, marca.getNombre());
            ps.setBigDecimal(2, marca.getPrecio());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("id")) {
            marca.setId(((Number) keys.get("id")).longValue());
        }
        return marca;
    }

    @Override
    public RowMapper<Marca> marcaRowMapper() {
        return (rs, rowNum) -> {
            Marca marca = new Marca();
            marca.setId(rs.getLong("id"));
            marca.setNombre(rs.getString("nombre"));
            marca.setPrecio(rs.getBigDecimal("precio"));
            return marca;
        };
    }

    @Override
    public Optional<Marca> buscarPorId(Long id) {
        String sql = "SELECT * FROM marcas WHERE id = ?";
        try {
            Marca marca = jdbcTemplate.queryForObject(sql, new Object[]{id}, marcaRowMapper());
            return Optional.ofNullable(marca);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Marca> listarTodos() {
        String sql = "SELECT * FROM marcas ORDER BY id";
        return jdbcTemplate.query(sql, marcaRowMapper());
    }

    @Override
    public List<Marca> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM marcas WHERE nombre like ?";
        return jdbcTemplate.query(sql,
                new Object[]{"%" + nombre + "%"},
                marcaRowMapper());
    }

    @Override
    public List<Marca> buscarPorPrecioMaximo(Double precioMaximo) {
        String sql = "SELECT * FROM marcas WHERE precio <= ?";
        return jdbcTemplate.query(sql,
                new Object[]{precioMaximo},
                marcaRowMapper());
    }

    @Override
    public boolean actualizar(Marca marca) {
        String sql = "UPDATE marcas SET nombre = ?,  precio = ?   WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                marca.getNombre(),
                marca.getPrecio(),
                marca.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean eliminar(Long id) {
        String sql = "DELETE FROM marcas WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
