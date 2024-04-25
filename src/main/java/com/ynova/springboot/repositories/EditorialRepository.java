package com.ynova.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynova.springboot.entities.Editorial;
import com.ynova.springboot.entities.Libro;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long> {

    Optional<Editorial> findByNombre(String nombre);

}
