package com.Biblioteca.Prestamos.Entidades;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="libro")
public class Libro {


    @Id
    @Column(unique = true, length = 30)
    private String isbn;
    @Column(unique = false, length = 50)
    private String titulo;
    @Column(unique = false, length = 50)
    private String editorial;
    @Column(unique = false, length = 30)
    private String autor;
    @Column(nullable = true)
    private int num_pag;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Prestamo> prestamos;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String editorial, String autor, int num_pag) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.autor = autor;
        this.num_pag = num_pag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNum_pag() {
        return num_pag;
    }

    public void setNum_pag(int num_pag) {
        this.num_pag = num_pag;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", autor='" + autor + '\'' +
                ", num_pag=" + num_pag +
                '}';
    }
}
