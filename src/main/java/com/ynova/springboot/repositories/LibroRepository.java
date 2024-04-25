package com.ynova.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynova.springboot.entities.Categoria;
import com.ynova.springboot.entities.Libro;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findByCategoria(Categoria categoria);

}
