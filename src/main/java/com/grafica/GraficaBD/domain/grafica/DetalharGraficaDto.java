package com.grafica.GraficaBD.domain.grafica;

import com.grafica.GraficaBD.domain.contrato.DetalharContratosDeGraficaContradaDto;

import java.util.List;

public record DetalharGraficaDto(

        String nome,

        String tipo_grafica,

        String endereco,

        List<DetalharContratosDeGraficaContradaDto> contratos,

        List<DetalharImpressoesGraficaDto> impressoes
) {

    public DetalharGraficaDto (Grafica grafica, List<DetalharImpressoesGraficaDto> detalharImpressoesDto, List<DetalharContratosDeGraficaContradaDto> detalharContratoDto) {
        this(grafica.getNome(),
                grafica instanceof GraficaContratada ? "Contratada" : grafica instanceof GraficaParticular ? "Particular"  : "Tipo Desconhecido",
                grafica instanceof GraficaContratada ? ((GraficaContratada) grafica).getEndereco() : null,
                detalharContratoDto,
                detalharImpressoesDto);
    }
}
