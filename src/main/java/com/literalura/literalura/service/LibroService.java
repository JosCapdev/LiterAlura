package com.literalura.literalura.service;

import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    @Autowired
    LibroRepository repository;
    Scanner teclado = new Scanner(System.in);

    public List<Libro> listarLibros(){
        return repository.findAll();
    }

    public void guardarLibro(Libro libro) {
        Optional<Libro> existente = repository.findByTituloIgnoreCase(libro.getTitulo());
        if (existente.isPresent()) {
            System.out.println(" El libro ya está registrado: " + existente.get().getTitulo());
        } else {
            repository.save(libro);
            System.out.println(" Libro guardado correctamente: " + libro.getTitulo());
        }
    }

    public void listarLibroXIdioma(){
        String menu=
                """
                    ----------------
                         Elija el idioma:
                            1 - [ES] Español
                            2 - [EN] Inglés
                            3 - [FR] Francés
                            4 - [DE] Alemán
                            
                            0 - Salir
                    ----------------
                    """
        ;
        var opcion = -1;
        String idioma="";

        while (opcion != 0) {
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        idioma="es";
                        ListaFiltrada(idioma);
                        break;
                    case 2:
                        idioma="en";
                        ListaFiltrada(idioma);
                        break;
                    case 3:
                        idioma="fr";
                        ListaFiltrada(idioma);
                        break;

                    case 4:
                        idioma="de";
                        ListaFiltrada(idioma);
                        break;

                    case 0:
                        System.out.println("volviendo al menu...");
                        break;
                    default:
                        System.out.println("Opción invalida inténtelo nuevamente");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida inténtelo nuevamente");
                teclado.nextLine();
            }

        }
    }

    private void ListaFiltrada(String idioma) {
        List<Libro> libros = repository.findAll();
        List<Libro> librosFiltrados = libros.stream() .filter(l -> l.getIdiomas().contains(idioma))
                .toList();
        System.out.println("---------------");
        System.out.println(" Libros en idioma [" + idioma.toUpperCase() + "]: " + librosFiltrados.size()+"\n");
        librosFiltrados.forEach(l -> System.out.println("- " + l.getTitulo()+"\n"));
    }

}
