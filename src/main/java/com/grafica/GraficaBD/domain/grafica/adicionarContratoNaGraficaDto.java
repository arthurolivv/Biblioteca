package com.grafica.GraficaBD.domain.grafica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.ContentType;

public record adicionarContratoNaGraficaDto(

        @NotNull
        Double valor,

        @NotBlank
        String nome_responsavel
) {

    public adicionarContratoNaGraficaDto (Contrato contrato){
        this(contrato.getValor(), contrato.getNome_responsavel());
    }
}
