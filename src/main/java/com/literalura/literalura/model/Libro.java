package com.literalura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String título;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private List<String> idiomas;
    private double numeroDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.título= datosLibro.titulo();
        this.idiomas=datosLibro.idiomas();
        this.numeroDescargas=datosLibro.numeroDescargas();

    }

    @Override
    public String toString() {
        return  "título='" + título + '\'' +
                ", autor=" + (autor !=null ? autor.getNombre(): "N/A") +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas
                ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
