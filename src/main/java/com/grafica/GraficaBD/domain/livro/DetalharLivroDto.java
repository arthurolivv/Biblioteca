package com.grafica.GraficaBD.domain.livro;

import com.grafica.GraficaBD.domain.escreve.Escreve;

import java.time.LocalDate;
import java.util.List;

public record DetalharLivroDto(

        String ISBN,

        String titulo,

        LocalDate data_de_publicacao,

        Long editora_id,

        List<DetalharNomeRgAutorDto> autores

) {
    public DetalharLivroDto (Livro livro,  List<DetalharNomeRgAutorDto> detalharNomeRgAutorDto){
        this(   livro.getIsbn(),
                livro.getTitulo(),
                livro.getData_de_publicacao(),
                livro.getEditora() != null ? livro.getEditora().getId() : null,
                detalharNomeRgAutorDto);
    }

    public DetalharLivroDto(Livro livro) {

        this(livro.getIsbn(),
                livro.getTitulo(),
                livro.getData_de_publicacao(),
                livro.getEditora() != null ? livro.getEditora().getId() : null,
                livro.getEscreve().stream().map(Escreve::getAutor).map(DetalharNomeRgAutorDto::new).toList());
    }
}
