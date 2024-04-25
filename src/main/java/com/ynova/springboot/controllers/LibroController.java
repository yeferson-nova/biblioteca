package com.ynova.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ynova.springboot.entities.Autor;
import com.ynova.springboot.entities.Categoria;
import com.ynova.springboot.entities.Editorial;
import com.ynova.springboot.entities.Libro;
import com.ynova.springboot.services.AutorService;
import com.ynova.springboot.services.CategoriaService;
import com.ynova.springboot.services.EditorialService;
import com.ynova.springboot.services.LibroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar","/"})
    public String listarLibros(Model model){
        List<Libro> libros = libroService.listarTodosLosLibros();
        model.addAttribute("libros",libros);
        return "libro/listar_libros";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoLibro(Model model){
        Libro libro = new Libro();
        model.addAttribute("libro",libro);
        model.addAttribute("editoriales",editorialService.listarTodasLasEditoriales());
        model.addAttribute("categorias",categoriaService.listarTodasLasCategorias());
        model.addAttribute("autores",autorService.listarTodosLosAutores());
        return "libro/formulario_libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro, @RequestParam("editorialId") Long editorialId,
                                @RequestParam("categoriaId") Long categoriaId,@RequestParam("autoresIds") List<Long> autoresIds){
        //Obtener y asignar la editorial y la categoría al libro
        Optional<Editorial> editorial = editorialService.buscarPorId(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
        categoria.ifPresent(libro::setCategoria);

        List<Autor> autores = autorService.buscarPorIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));

        libroService.saveLibro(libro);
        return "redirect:/libros/listar";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarLibro(@PathVariable Long id,Model model){
        Optional<Libro> libro = libroService.buscarPorId(id);

        if(libro.isPresent()){
            model.addAttribute("libro",libro.get());
            model.addAttribute("editoriales",editorialService.listarTodasLasEditoriales());
            model.addAttribute("categorias",categoriaService.listarTodasLasCategorias());
            model.addAttribute("autores",autorService.listarTodosLosAutores());
        }
        return "libro/formulario_libro";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarLibro(@PathVariable Long id ,@ModelAttribute Libro libro,
                                  @RequestParam("editorialId") Long editorialId,
                                  @RequestParam("categoriaId") Long categoriaId,
                                  @RequestParam("autoresIds") List<Long> autoresIds){
        //Obtener y asignar la editorial y la categoría al libro
        Optional<Editorial> editorial = editorialService.buscarPorId(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
        categoria.ifPresent(libro::setCategoria);

        List<Autor> autores = autorService.buscarPorIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));

        libro.setId(id);
        libroService.actualizarLibro(libro);
        return "redirect:/libros/listar";
    }

    @GetMapping("/{id}/autores")
    public String mostrarAutoresDelLibro(@PathVariable Long id,Model model){
        Optional<Libro> libroOptional = libroService.buscarPorId(id);
        if(libroOptional.isPresent()){
            Libro libro = libroOptional.get();
            model.addAttribute("libro",libro);
            model.addAttribute("autores",libro.getAutores());
        }
        return "libro/mostrar_autores_libro";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarLibro(@PathVariable Long id){
        libroService.eliminarLibro(id);
        return "redirect:/libros/listar";
    }
}
