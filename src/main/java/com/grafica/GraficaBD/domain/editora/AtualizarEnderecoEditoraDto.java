package com.grafica.GraficaBD.domain.editora;

import jakarta.validation.constraints.NotBlank;


public record AtualizarEnderecoEditoraDto(

    @NotBlank
    String endereco
){}
