package com.grafica.GraficaBD.domain.autor;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CriarAutorDto(

        @NotBlank
        String RG,

        @NotBlank
        String nome,

        @NotBlank
        String endereco,

        List<String> livros_isbn
) {
}
