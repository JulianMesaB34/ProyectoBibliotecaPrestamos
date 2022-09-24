package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Repositorio.EstudianteRepositorio;
import com.Biblioteca.Prestamos.Repositorio.libroRepositorio;
import com.Biblioteca.Prestamos.Repositorio.prestamoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PrestamosServicios {

    private prestamoRepositorio pRepo;
    private libroRepositorio librepo;
    private EstudianteRepositorio estRepo;

    public PrestamosServicios(prestamoRepositorio pRepo, libroRepositorio librepo, EstudianteRepositorio estRepo) {
        this.pRepo = pRepo;
        this.librepo = librepo;
        this.estRepo = estRepo;
    }

    public Prestamo agregarPrestamo(String isbn, String doc, Prestamo prestamo) {

        librepo.findById(isbn).map(libro -> {
            prestamo.setLibro(libro);
            return libro;
        });

        return estRepo.findById(doc).map(est -> {
            prestamo.setEstudiante(est);
            return pRepo.save(prestamo);
        }).get();
    }


    public ArrayList<Prestamo> porEstudiante(String doc) {
        try {
            return estRepo.findById(doc).map(est -> {
                return pRepo.findByEstudiante(est);
            }).get();
        }catch(Exception ex){
            System.out.println("Error"+ex);

        }
        return null;
    }



}
