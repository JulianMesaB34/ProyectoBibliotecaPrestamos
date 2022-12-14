package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Servicios.LibroServicios;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class LibroControlador {

    private LibroServicios servicio;

    public LibroControlador(LibroServicios servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/ListarLibros")
    public ArrayList<Libro> listar(){
        return servicio.listarLibros();
    }

    @GetMapping("/BuscarLibros/{id}")
    public Optional<Libro> buscarLibros(@PathVariable("id") String isbn){
        return servicio.buscarLibros(isbn);
    }

    @GetMapping("/BuscarAutor/{autor}")
    public ArrayList<Libro> buscarAutor(@PathVariable("autor") String autor){
        return  servicio.buscarAutor(autor);
    }

    /*@PostMapping("/AgregarLibro")
    public String agregarLibro(@RequestBody Libro libro){
        return servicio.agregarLibro(libro);
    }*/

    @PutMapping("/ActualizarLibro")
    public String actualizarLibro(@RequestBody Libro libro){
        return servicio.actualizarLibro(libro);
    }

    @PatchMapping("/ActualizarEdit/{isbn}/{edit}")
    public String actualizarEdit(@PathVariable("isbn") String isbn, @PathVariable("edit") String editorial){
        return servicio.actualizarEditorial(isbn, editorial);
    }

    @PatchMapping("ActualizarCampo/{isbn}")
    public Libro actualizarCampo(@PathVariable("isbn") String isbn, @RequestBody Map<Object, Object> libroMap){
        return servicio.actualizarCampo(isbn,libroMap);
    }
    @DeleteMapping("EliminarLibro/{id}")
    public String eliminarLibro(@PathVariable("id") String isbn){
        return servicio.eliminarLibro(isbn);
    }
}
