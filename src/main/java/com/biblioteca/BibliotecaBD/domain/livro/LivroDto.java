package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LivroDto(

        @NotNull
        Long ISBN,

        @NotBlank
        String titulo,

        @NotBlank
        LocalDate data_de_publicacao,

        @NotNull
        Editora editora

) {
}
