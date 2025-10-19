package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LivroIsbnDto(

        @NotNull
        String ISBN
) {
}
