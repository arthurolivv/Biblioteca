package com.grafica.GraficaBD.domain.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CadastrarLivroDto(

        @NotBlank
        String ISBN,

        @NotBlank
        String titulo,

        @NotNull
        LocalDate data_de_publicacao,

        Long editora_id,

        List<String> autores_rg

) {

}
