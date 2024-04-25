package com.ynova.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynova.springboot.entities.Autor;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombre(String nombre);

}
