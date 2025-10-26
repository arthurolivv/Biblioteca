package com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;

public interface ValidadorVincularLivro {

    void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto);
}
