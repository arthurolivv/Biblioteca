package com.grafica.GraficaBD.domain.grafica;

import java.util.List;

public record DetalharGraficaDto(

        String nome,

        String tipo_grafica,

        String endereco,

        List<DetalharContratosDto> contratos,

        List<DetalharImpressoesGraficaDto> impressoes
) {

    public DetalharGraficaDto (Grafica grafica, List<DetalharImpressoesGraficaDto> detalharImpressoesDto, List<DetalharContratosDto> detalharContratoDto) {
        this(grafica.getNome(),
                grafica instanceof GraficaContratada ? "Contratada" : grafica instanceof GraficaParticular ? "Particular"  : "Tipo Desconhecido",
                grafica instanceof GraficaContratada ? ((GraficaContratada) grafica).getEndereco() : null,
                detalharContratoDto,
                detalharImpressoesDto);
    }
}
