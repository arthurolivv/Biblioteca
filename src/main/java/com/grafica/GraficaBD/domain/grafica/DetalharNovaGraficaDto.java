package com.grafica.GraficaBD.domain.grafica;

public record DetalharNovaGraficaDto(

        Long id,

        String nome,

        String tipo_grafica,

        String endereco
) {

    public DetalharNovaGraficaDto (Grafica grafica){
        this(   grafica.getId(),
                grafica.nome,
                grafica instanceof GraficaContratada ? "Contratada" : "Particular",
                grafica instanceof GraficaContratada ? ((GraficaContratada) grafica).getEndereco() : null);
    }
}
