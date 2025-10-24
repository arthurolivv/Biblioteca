package com.grafica.GraficaBD.domain.grafica;

import com.grafica.GraficaBD.domain.imprime.Imprime;
import com.grafica.GraficaBD.domain.livro.Livro;

import java.time.LocalDate;

public record DetalharImpressoesGraficaDto(

        String nome,

        String lisbn,

        String livro_titulo,

        Long nto_copias,

        LocalDate data_entrega
) {
}
