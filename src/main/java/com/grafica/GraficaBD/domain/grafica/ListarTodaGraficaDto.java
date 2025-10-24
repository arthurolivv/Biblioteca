package com.grafica.GraficaBD.domain.grafica;

public record ListarTodaGraficaDto(

        Long id,

        String nome,

        String tipo,

        String endereco
) {
    public ListarTodaGraficaDto(Grafica grafica){
        this(
                grafica.getId(),
                grafica.getNome(),
                grafica instanceof GraficaParticular ? "Particular"
                        : grafica instanceof GraficaContratada ? "Contratada"
                        : "Desconhecido",
                grafica instanceof GraficaContratada contratada ? contratada.getEndereco() : null
        );
    }
}
