package com.grafica.GraficaBD.domain.grafica;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CadastrarGraficaDto(

        @NotBlank
        String nome,

        @NotNull
        TipoGrafica tipo_grafica,

        String endereco
) {
}
