package com.demo.productmanagement.repository;


import com.demo.productmanagement.model.Marca;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public interface MarcaRepository {

    // CREATE
    Marca guardar(Marca marca);
    // READ
    RowMapper<Marca> marcaRowMapper();

    Optional<Marca> buscarPorId(Long id);

    List<Marca> listarTodos();

    List<Marca> buscarPorNombre(String nombre);

    List<Marca> buscarPorPrecioMaximo(Double precioMaximo);

    // UPDATE
    boolean actualizar(Marca marca);

    // DELETE
    boolean eliminar(Long id);
}