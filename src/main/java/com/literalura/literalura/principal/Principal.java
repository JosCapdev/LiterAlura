package com.literalura.literalura.principal;

import com.literalura.literalura.model.*;
import com.literalura.literalura.service.AutorService;
import com.literalura.literalura.service.ConsumoAPI;
import com.literalura.literalura.service.ConvierteDatos;
import com.literalura.literalura.service.LibroService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private String json;
    private ConvierteDatos conversor = new ConvierteDatos();
    private String menu = """
            ----------------
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Buscar autor por nombre
                    7 - Top 10 libros más descargados
                    8 - Estadísticas de descarga
                    
                    0 - Salir
            """;
    private LibroService libroService;
    private AutorService autorService;
    private List<Autor> autores;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService; this.autorService = autorService;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroXTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;

                    case 4:
                        listarAutoresVivosXAnio();
                        break;
                    case 5:
                        listarLibrosXIdioma();
                        break;
                    case 6:
                        System.out.println("Ingrese el nombre del autor:");
                        String nombre = teclado.nextLine();
                        buscarAutorXNombre(nombre);
                        break;
                    case 7:
                        top10LibrosMasDescargados();
                        break;
                    case 8:
                        estadisticasDescarga();
                        break;
                    case 0:
                        System.out.println("Muchas Gracias por su visita!");
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


    private void buscarLibroXTitulo() {
        System.out.println("Ingrese el título del libro:");
        var tituloBuscado = teclado.nextLine();
        json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloBuscado.replace(" ", "+"));
        DatosLibro datosLibro = getDatosLibro(tituloBuscado);
        Autor autor = null;

        if (datosLibro != null) {
            DatosAutor datosAutor = datosLibro.autor().isEmpty() ? null : datosLibro.autor().get(0);
            if (datosAutor != null) {
                autor = autorService.buscarOCrearAutor(datosAutor);
            }

            Libro libro = new Libro(datosLibro);
            libro.setAutor(autor);
            libroService.guardarLibro(libro);
            System.out.println(libro);
        }else{
            System.out.println("Libro no encontrado intente con otro título");
        }
    }


    private DatosLibro getDatosLibro(String tituloBuscado) {
        Results datos = conversor.obtenerDatos(json, Results.class);
        return datos.datosLibroList().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(tituloBuscado.toUpperCase()))
                .findFirst().orElse(null);
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarLibros();
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorService.autores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosXAnio() {
        System.out.println("Ingresa un determinado año:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine();
            autores = autorService.autoresVivosXAnio(anio);
            if (autores != null && !autores.isEmpty()) {
                System.out.println("En "+anio+" estaban los autores:");
                autores.forEach(System.out::println);
            } else {
                System.out.println("No hay autores en ese año");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Debes ingresar un año determinado");
            teclado.nextLine();
        }
    }

    private void listarLibrosXIdioma() {
        libroService.listarLibroXIdioma();
    }

    private void buscarAutorXNombre(String nombre) {
        List<Autor> autores = autorService.buscarAutorNombre(nombre);
        System.out.println("---------------");
        if (autores.isEmpty()) {
            System.out.println("Autor no encontrado...");
        } else {
            System.out.println("Autores encontrados:");
            autores.forEach(a -> System.out.println(
                    "- " + a.getNombre() +
                            " (" + a.getAnioNacimiento() +
                            (a.getAnioFallecimiento() != null ? " - " + a.getAnioFallecimiento() : "") + ")"
            ));
        }
    }


    private void top10LibrosMasDescargados() {
        libroService.top10Descargas();
    }

    private void estadisticasDescarga() {
        libroService.estadisticasDescarga();
    }

}
