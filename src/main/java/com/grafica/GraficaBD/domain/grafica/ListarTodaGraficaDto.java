package com.grafica.GraficaBD.domain.grafica;

public record ListarTodaGraficaDto(

        Long id,

        String nome,

        String tipo
) {
    public ListarTodaGraficaDto(Grafica grafica){
        this(
                grafica.getId(),
                grafica.getNome(),
                grafica instanceof GraficaParticular ? "Particular"
                        : grafica instanceof GraficaContratada ? "Contratada"
                        : "Desconhecido"
        );
    }
}
