package com.literalura.literalura.service;

import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    LibroRepository repository;

    public List<Libro> listarLibros(){
        return repository.findAll();
    }

    public void guardarLibro(Libro libro){
        repository.save(libro);
    }

    public void listarLibroXIdioma(){
        List<Libro> libros = repository.findAll();
        Map<String, Long> estadisticas = libros.stream()
                .flatMap(l -> l.getIdiomas().stream())
                .collect(Collectors.groupingBy(idioma -> idioma, Collectors.counting()));
         estadisticas.forEach((idioma,cantidad)->System.out.println("Idioma ["+idioma.toUpperCase()
                 +"] cantidad de libros: "+cantidad));
    }
}
