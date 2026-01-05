package com.literalura.literalura.service;

import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public void top10Descargas() {
        List<Libro> top10= repository.findTop10ByOrderByNumeroDescargasDesc();
        System.out.println("---------------");
        top10.forEach(l -> System.out.println("Libro: "+l.getTitulo()+" Descargas: "+l.getNumeroDescargas()));
    }

    public void estadisticasDescarga() {
        DoubleSummaryStatistics est = repository.findAll().stream()
                .filter(d -> d.getNumeroDescargas() >0 )
                .collect(Collectors.summarizingDouble(Libro::getNumeroDescargas));
        System.out.println("---------------");
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad máxima de descargas: "+ est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println(" Cantidad de registros evaluados para calcular las estadisticas: " + est.getCount());
    }
}
