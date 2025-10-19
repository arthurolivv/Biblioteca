package com.grafica.GraficaBD.domain.livro;

import java.time.LocalDate;

public record ListarLivroDto(

        String ISBN,

        String titulo,

        LocalDate data_de_publicacao,

        Long editora_id

) {
    public ListarLivroDto (Livro livro){
        this(   livro.getIsbn(),
                livro.getTitulo(),
                livro.getData_de_publicacao(),
                livro.getEditora() != null ? livro.getEditora().getId() : null);
    }
}
