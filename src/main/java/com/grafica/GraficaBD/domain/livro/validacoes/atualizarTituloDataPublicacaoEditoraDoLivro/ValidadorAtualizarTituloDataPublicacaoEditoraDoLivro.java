package com.grafica.GraficaBD.domain.livro.validacoes.atualizarTituloDataPublicacaoEditoraDoLivro;

import com.grafica.GraficaBD.domain.livro.AtualizarTituloDataPublicacaoEditoraDoLivroDto;

public interface ValidadorAtualizarTituloDataPublicacaoEditoraDoLivro {

    void validar(String isbn, AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivro);
}
