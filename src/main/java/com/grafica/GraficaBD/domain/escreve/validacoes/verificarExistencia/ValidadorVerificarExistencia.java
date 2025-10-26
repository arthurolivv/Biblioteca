package com.grafica.GraficaBD.domain.escreve.validacoes.verificarExistencia;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;

public interface ValidadorVerificarExistencia {

    void validar(String rg, LivrosPorIsbnDto livrosPorIsbnDto);

}
