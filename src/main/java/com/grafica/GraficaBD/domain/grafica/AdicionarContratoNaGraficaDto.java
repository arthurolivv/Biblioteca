package com.grafica.GraficaBD.domain.grafica;

import com.grafica.GraficaBD.domain.contrato.Contrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdicionarContratoNaGraficaDto(

        @NotNull
        Double valor,

        @NotBlank
        String nome_responsavel
) {

    public AdicionarContratoNaGraficaDto(Contrato contrato){
        this(contrato.getValor(), contrato.getNome_responsavel());
    }
}
