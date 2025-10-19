package com.grafica.GraficaBD.domain.editora;

public record ListarEditoraDto(

        Long id,

        String nome,

        String endereco
) {

    public ListarEditoraDto(Editora editora) {
        this(editora.getId(), editora.getNome(), editora.getEndereco());
    }
}
