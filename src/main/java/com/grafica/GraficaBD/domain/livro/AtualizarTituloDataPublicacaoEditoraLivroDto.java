package com.grafica.GraficaBD.domain.livro;

import java.time.LocalDate;
import java.util.List;

public record AtualizarTituloDataPublicacaoEditoraLivroDto(

    String titulo,

    LocalDate data_de_publicacao,

    Long editora_id,

    List<String> addAutorRg,

    List<String> remAutorRg
        ){
}
