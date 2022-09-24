package com.Biblioteca.Prestamos.Controlador;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Servicios.PrestamosServicios;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PrestamoControlador {

    PrestamosServicios servicio;

    public PrestamoControlador(PrestamosServicios servicio) {
        this.servicio = servicio;
    }

    @PostMapping("AgregarPrestamo/{isbn}/{doc}")
    public Prestamo agregarPrestamo(@PathVariable("isbn")String isbn, @PathVariable("doc")String doc, @RequestBody Prestamo prestamo){
        return servicio.agregarPrestamo(isbn,doc,prestamo);
    }

    @GetMapping("/porEstudiante/{doc}")
    public ArrayList<Prestamo> porEstudiante(@PathVariable("doc")String doc){
        return servicio.porEstudiante(doc);
    }
}
