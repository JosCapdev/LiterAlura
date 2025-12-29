package com.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") String autor,
    @JsonAlias("lenguages") String idiomas,
    @JsonAlias("download_count") double numeroDescargas
    ){
}
