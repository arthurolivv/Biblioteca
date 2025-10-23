package com.grafica.GraficaBD.domain.grafica;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record CadastrarGraficaDto(

        @NotBlank
        String nome,

        @NotBlank
        TipoGrafica tipoGrafica,

        String endereco
) {
}
