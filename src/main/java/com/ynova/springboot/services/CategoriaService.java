package com.ynova.springboot.services;

import java.util.List;
import java.util.Optional;

import com.ynova.springboot.entities.Categoria;

public interface CategoriaService {

    Categoria guardarCategoria(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    Optional<Categoria> buscarPorNombre(String nombre);

    List<Categoria> listarTodasLasCategorias();

    Categoria actualizarCategoria(Categoria categoria);

    void eliminarCategoria(Long id);

}
