package com.grafica.GraficaBD.domain.editora;

import jakarta.validation.constraints.NotBlank;


public record CriarEditoraDto(

    @NotBlank
    String nome,

    @NotBlank
    String endereco
){
    public CriarEditoraDto(Editora editora) {
        this(editora.getNome(), editora.getEndereco());
    }
}
