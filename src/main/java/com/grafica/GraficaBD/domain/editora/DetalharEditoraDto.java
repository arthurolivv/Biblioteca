package com.grafica.GraficaBD.domain.editora;

import java.util.ArrayList;
import java.util.List;

public record DetalharEditoraDto(

        Long id,

        String nome,

        String endereco,

        List<String> livros
) {
    public DetalharEditoraDto(Editora editora) {
        this(editora.getId(), editora.getNome(), editora.getEndereco(), new ArrayList<>());
    }
}
