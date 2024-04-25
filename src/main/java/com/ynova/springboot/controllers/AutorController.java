package com.ynova.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ynova.springboot.entities.Autor;
import com.ynova.springboot.services.AutorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar","/"})
    public String listarAutores(Model model){
        List<Autor> autores = autorService.listarTodosLosAutores();
        model.addAttribute("autores",autores);
        return "autor/lista_autores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoAutor(Model model){
        model.addAttribute("autor",new Autor());
        return "autor/formulario_autor";
    }

    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute Autor autor){
        autorService.guardarAutor(autor);
        return "redirect:/autores/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAutor(@PathVariable Long id,Model model){
        Optional<Autor> autor = autorService.buscarPorId(id);
        autor.ifPresent(value -> model.addAttribute("autor",value));
        return "autor/formulario_autor";
    }

    @PostMapping("/actualizar")
    public String actualizarAutor(@ModelAttribute Autor autor){
        autorService.actualizarAutor(autor);
        return "redirect:/autores/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id) throws ClassNotFoundException {
        autorService.eliminarAutor(id);
        return "redirect:/autores/listar";
    }
}
