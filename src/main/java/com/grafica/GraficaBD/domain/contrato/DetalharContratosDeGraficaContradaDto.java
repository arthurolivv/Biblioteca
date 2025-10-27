package com.grafica.GraficaBD.domain.contrato;

public record DetalharContratosDeGraficaContradaDto(

        Long id,

        Double valor,

        String nome_responsavel
) {

    public DetalharContratosDeGraficaContradaDto(Contrato contrato){
        this(contrato.getId(), contrato.getValor(), contrato.getNome_responsavel());
    }

}
