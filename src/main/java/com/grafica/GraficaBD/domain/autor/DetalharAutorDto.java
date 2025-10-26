package com.grafica.GraficaBD.domain.autor;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.livro.Livro;

import java.util.List;

public record DetalharAutorDto(

        String nome,

        String endereco,

        List<DetalharLivroSemAutorDto> livros
) {
    public DetalharAutorDto (Autor autor){
        this(autor.getNome(), autor.getEndereco(), autor.getEscreve().stream().map(Escreve::getLivro).map(DetalharLivroSemAutorDto::new).toList());
    }
}
