package com.grafica.GraficaBD.domain.escreve.validacoes.vinculacaoAutorELivros;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;

public interface ValidadorVinculacaoAutorELivros {

    void validar(String rg, LivrosPorIsbnDto livrosPorIsbnDto);

}
