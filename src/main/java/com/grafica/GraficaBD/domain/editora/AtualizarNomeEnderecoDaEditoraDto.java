package com.grafica.GraficaBD.domain.editora;

import jakarta.validation.constraints.NotBlank;


public record AtualizarNomeEnderecoDaEditoraDto(

    String nome,

    String endereco
){}
