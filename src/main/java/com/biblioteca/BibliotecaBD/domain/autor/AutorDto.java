package com.biblioteca.BibliotecaBD.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record AutorDto(

        @NotBlank
        String RG,

        @NotBlank
        String nome,

        @NotBlank
        String endereco
) {
}
