package com.grafica.GraficaBD.domain.livro;

import java.time.LocalDate;

public record AtualizarTituloDataPublicacaoEditoraDoLivroDto(

    String titulo,

    LocalDate data_de_publicacao,

    Long editora_id
) {
}
