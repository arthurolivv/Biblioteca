package com.biblioteca.BibliotecaBD.domain.editora;

import jakarta.validation.constraints.NotBlank;

public record ListarEditoraDto(

        Long id,

        String nome,

        String endereco
) {

    public ListarEditoraDto(Editora editora) {
        this(editora.getId(), editora.getNome(), editora.getEndereco());
    }
}
