package com.demo.productmanagement.service;

import com.demo.productmanagement.model.Marca;

import java.util.List;
import java.util.Optional;

public interface MarcaService {
    List<Marca> listarTodosLasMarcas();

    Optional<Marca> obtenerMarcaPorId(Long id);

    Optional<Marca> guardarMarca(Marca marca);

    boolean actualizarMarca(Marca marca);

    boolean eliminarMarca(Long id);

    List<Marca> buscarMarcasPorNombre(String nombre);

    List<Marca> buscarMarcasPorPrecioMaximo(Double precioMaximo);



}
