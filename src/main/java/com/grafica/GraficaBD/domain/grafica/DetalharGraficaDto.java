package com.grafica.GraficaBD.domain.grafica;

import java.util.List;

public record DetalharGraficaDto(

        String nome,

        String tipo_grafica,

        String endereco,

        List<DetalharImpressoesGraficaDto> impressoes
) {

}
