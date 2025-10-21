package com.grafica.GraficaBD.domain.autor;

import com.grafica.GraficaBD.domain.livro.Livro;

import java.util.List;

public record AtualizarNomeEnderecoLivroAutorDto(

        String nome,

        String endereco,

        List<String> addLivrosIsbn,

        List<String> remLivrosIsbn
) {
}
