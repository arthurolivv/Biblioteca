package com.grafica.GraficaBD.domain.livro;

import jakarta.validation.constraints.NotNull;

public record LivroIsbnDto(

        @NotNull
        String ISBN
) {
}
