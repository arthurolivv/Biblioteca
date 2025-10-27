package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.contrato.Contrato;
import com.grafica.GraficaBD.domain.grafica.Grafica;
import com.grafica.GraficaBD.domain.grafica.GraficaContratada;

public record ListarTodosOsContratosDto(

        Long id_contrato,

        Double valor,

        String nome_responsavel,

        Long grafica_cont_id,

        String grafica_cont_nome

) {

    public ListarTodosOsContratosDto (Contrato contrato) {

        this(contrato.getId(),
                contrato.getValor(),
                contrato.getNome_responsavel(),
                contrato.getGrafica_cont().getId(),
                contrato.getGrafica_cont().getNome());
    }
}
