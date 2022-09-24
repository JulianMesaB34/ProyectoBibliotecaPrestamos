package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Libro;
import com.Biblioteca.Prestamos.Repositorio.libroRepositorio;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class LibroServicios {

    private libroRepositorio repositorio;

    public LibroServicios(libroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<Libro> listarLibros() { //Consultar todos
        return (ArrayList<Libro>) repositorio.findAll();
    }

    public Optional<Libro> buscarLibros(String isbn) {
        return repositorio.findById(isbn);
    }

    public Libro buscarLibros1(String isbn) {
        return repositorio.findById(isbn).orElse(null);
    }

    public ArrayList<Libro> buscarAutor(String autor) {
        return repositorio.findByAutor(autor);
    }

    public boolean agregarLibro(Libro libro) {

        if (!buscarLibros(libro.getIsbn()).isPresent()) {
            repositorio.save(libro);
            return true;
        } else {
            return false;
        }
    }

    public String actualizarLibro(Libro libro) {
        if (buscarLibros(libro.getIsbn()).isPresent()) {
            repositorio.save(libro);
            return "Libro actualzado con éxito";
        }else{
            return "El libro a modificar no existe.";
        }
    }

    public String actualizarEditorial(String isbn, String editorial){
        if(buscarLibros(isbn).isPresent()){
            Libro libro=repositorio.findById(isbn).get();
            libro.setEditorial(editorial);
            repositorio.save(libro);
            return "Editorial Actualizada";
        }else{
            return "Libro a actualizar no existe";
        }
    }

    public Libro actualizarCampo(String isbn, Map<Object,Object> libroMap){
        Libro libro=repositorio.findById(isbn).get();
        libroMap.forEach((key,value)->{
            Field campo= ReflectionUtils.findField(Libro.class,(String) key);
            campo.setAccessible(true);
            ReflectionUtils.setField(campo, libro, value);
        });
        return repositorio.save(libro);
    }

    public String eliminarLibro(String isbn){
        if(buscarLibros(isbn).isPresent()){
            repositorio.deleteById(isbn);
            return "Libro Eliminado";
        }else{
            return "Libro a eliminar no existe";
        }
    }
}
