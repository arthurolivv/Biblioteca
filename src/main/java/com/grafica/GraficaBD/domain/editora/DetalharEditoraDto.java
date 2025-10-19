package com.grafica.GraficaBD.domain.editora;

import java.util.List;

public record DetalharEditoraDto(

        Long id,

        String nome,

        String endereco,

        List<String> livros
) {
}
