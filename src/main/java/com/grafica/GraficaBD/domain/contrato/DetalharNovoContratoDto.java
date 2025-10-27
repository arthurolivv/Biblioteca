package com.grafica.GraficaBD.domain.contrato;

public record DetalharNovoContratoDto(

        Long id,

        Double valor,

        String nome_responsavel
) {

    public DetalharNovoContratoDto (Contrato contrato){
        this(contrato.getId(),contrato.getValor(),contrato.getNome_responsavel());
    }
}
