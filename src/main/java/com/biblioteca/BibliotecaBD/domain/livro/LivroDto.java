package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LivroDto(

        @NotBlank
        String ISBN,

        @NotBlank
        String titulo,

        @NotNull
        LocalDate data_de_publicacao,

        Long editora_id

) {
}
