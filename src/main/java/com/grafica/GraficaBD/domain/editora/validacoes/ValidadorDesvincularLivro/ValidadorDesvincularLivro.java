package com.grafica.GraficaBD.domain.editora.validacoes.ValidadorDesvincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.livro.Livro;

public interface ValidadorDesvincularLivro {

    void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto);
}
