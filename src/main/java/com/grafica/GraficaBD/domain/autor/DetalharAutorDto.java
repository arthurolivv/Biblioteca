package com.grafica.GraficaBD.domain.autor;

import com.grafica.GraficaBD.domain.livro.Livro;

import java.util.List;

public record DetalharAutorDto(

        String nome,

        String endereco,

        List<DetalharLivroSemAutorDto> livros
) {
}
