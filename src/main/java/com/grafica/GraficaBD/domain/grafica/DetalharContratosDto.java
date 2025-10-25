package com.grafica.GraficaBD.domain.grafica;

public record DetalharContratosDto(

        Long id,

        Double valor,

        String nome_responsavel
) {

    public DetalharContratosDto (Contrato contrato){
        this(contrato.getId(), contrato.getValor(), contrato.getNome_responsavel());
    }

}
