package com.grafica.GraficaBD.domain.autor;

public record ListarAutorDto(

        String RG,

        String Nome
) {
    public ListarAutorDto (Autor autor){
        this(autor.getRg(),  autor.getNome());
    }
}
