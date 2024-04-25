package com.ynova.springboot.services;

import java.util.List;
import java.util.Optional;

import com.ynova.springboot.entities.Categoria;
import com.ynova.springboot.entities.Libro;

public interface LibroService {

    Libro saveLibro(Libro libro);

    Optional<Libro> buscarPorId(Long id);

    Optional<Libro> buscarPorTitulo(String titulo);

    List<Libro> listarTodosLosLibros();

    Libro actualizarLibro(Libro libro);

    void eliminarLibro(Long id);

    List<Libro> buscarPorCategoria(Categoria categoria);
}
