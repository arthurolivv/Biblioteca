package com.grafica.GraficaBD.domain.livro;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AutoresPorRgDto(

        @NotEmpty
        List<String> autores_rg

) {
}
