package com.grafica.GraficaBD.domain.escreve.validacoes.criarEscreve;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;

public interface ValidadorCriarEscreve {

    void validar(String rg, LivrosPorIsbnDto livrosPorIsbnDto);

}
