package com.ynova.springboot.services;

import java.util.List;
import java.util.Optional;

import com.ynova.springboot.entities.Editorial;

public interface EditorialService {

    Editorial guardarEditorial(Editorial editorial);

    Optional<Editorial> buscarPorId(Long id);

    Optional<Editorial> buscarPorNombre(String nombre);

    List<Editorial> listarTodasLasEditoriales();

    Editorial actualizarEditorial(Editorial editorial);

    void eliminarEditorial(Long id);

}
