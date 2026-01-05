package com.literalura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)
    String nombre;
    String anioNacimiento;
    String anioFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioFallecimiento= datosAutor.anioFallecimiento();
    }

    @Override
    public String toString() {
        return  "------------------------------------\n" +
                "    AUTOR    "+ "\n" +
                "  nombre='" + nombre + '\'' + "\n" +
                ", año de nacimiento='" + anioNacimiento + '\'' + "\n" +
                ", año de fallecimiento='" + anioFallecimiento + '\'' + "\n" +
                ", libros=" + libros.stream()
                                    .map(Libro::getTitulo)
                                    .collect(Collectors.toList())+ "\n" ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(String anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
