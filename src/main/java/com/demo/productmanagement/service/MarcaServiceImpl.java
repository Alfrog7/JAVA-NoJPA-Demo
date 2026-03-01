package com.demo.productmanagement.service;

import com.demo.productmanagement.model.Marca;
import com.demo.productmanagement.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {
    @Autowired
    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Marca> listarTodosLasMarcas() {
        return marcaRepository.listarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Marca> obtenerMarcaPorId(Long id) {
        return marcaRepository.buscarPorId(id);
    }

    @Override
    @Transactional
    public Optional<Marca> guardarMarca(Marca marca) {
        Marca registro = marcaRepository.guardar(marca);
        return marcaRepository.buscarPorId(registro.getId());
    }

    @Override
    public boolean actualizarMarca(Marca marca) {
        return marcaRepository.actualizar(marca);

    }

    @Override
    @Transactional
    public boolean eliminarMarca(Long id) {
       return  marcaRepository.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Marca> buscarMarcasPorNombre(String nombre) {
        return marcaRepository.buscarPorNombre(nombre);
    }

    @Override
    public List<Marca> buscarMarcasPorPrecioMaximo(Double precioMaximo) {
        return marcaRepository.buscarPorPrecioMaximo(precioMaximo);
    }
}
