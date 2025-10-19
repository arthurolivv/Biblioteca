package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
