package com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores;

import com.grafica.GraficaBD.domain.livro.AutoresPorRgDto;

public interface ValidadorVinculacaoLivroEAutores {

    void validar(String isbn, AutoresPorRgDto autoresPorRgDto);
}
