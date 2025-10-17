package com.biblioteca.BibliotecaBD.domain.editora;

import jakarta.validation.constraints.NotBlank;

public record EditoraDto(

        @NotBlank
        String nome,

        @NotBlank
        String endereco
) {
}
