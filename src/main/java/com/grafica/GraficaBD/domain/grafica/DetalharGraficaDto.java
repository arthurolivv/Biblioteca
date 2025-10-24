package com.grafica.GraficaBD.domain.grafica;

import java.util.List;

public record DetalharGraficaDto(

        String nome,

        String tipo_grafica,

        String endereco,

        List<DetalharImpressoesGraficaDto> impressoes
) {

    public DetalharGraficaDto (Grafica grafica, List<DetalharImpressoesGraficaDto> detalharImpressoesDto) {
        this(grafica.getNome(),
                grafica instanceof GraficaContratada ? "Contratada" : grafica instanceof GraficaParticular ? "Particular"  : "Tipo Desconhecido",
                grafica instanceof GraficaContratada ? ((GraficaContratada) grafica).getEndereco() : null,
                detalharImpressoesDto);
    }
}
