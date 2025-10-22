package com.grafica.GraficaBD.domain.autor;

import com.grafica.GraficaBD.domain.livro.Livro;

import java.time.LocalDate;

public record DetalharLivroSemAutorDto(

    String ISBN,

    String titulo,

    LocalDate data_de_publicacao,

    Long editora_id


) {
    public DetalharLivroSemAutorDto (Livro livro){
            this(   livro.getIsbn(),
                    livro.getTitulo(),
                    livro.getData_de_publicacao(),
                    livro.getEditora() != null ? livro.getEditora().getId() : null);
        }
    }
