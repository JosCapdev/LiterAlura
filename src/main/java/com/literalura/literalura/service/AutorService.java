package com.literalura.literalura.service;

import com.literalura.literalura.model.Autor;
import com.literalura.literalura.model.DatosAutor;
import com.literalura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    AutorRepository repository;

    public List<Autor> autores(){
        return repository.findAll();
    }

    public Autor buscarOCrearAutor(DatosAutor datosAutor) {
        return repository.findByNombreIgnoreCase(datosAutor.nombre())
                .orElseGet(() -> repository.save(new Autor(datosAutor)));
    }

    public List<Autor> autoresVivosXAnio(int anio){
        return repository.autoresVivosEnAnio(anio);
    }

    public List <Autor> buscarAutorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }
}
