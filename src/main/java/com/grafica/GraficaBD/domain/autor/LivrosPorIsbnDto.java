package com.grafica.GraficaBD.domain.autor;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LivrosPorIsbnDto(

        @NotEmpty
        List<String> isbns
) {
}
