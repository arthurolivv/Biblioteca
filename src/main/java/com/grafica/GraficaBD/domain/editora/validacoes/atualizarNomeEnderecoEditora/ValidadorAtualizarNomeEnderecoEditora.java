package com.grafica.GraficaBD.domain.editora.validacoes.atualizarNomeEnderecoEditora;

import com.grafica.GraficaBD.domain.editora.AtualizarNomeEnderecoDaEditoraDto;

public interface ValidadorAtualizarNomeEnderecoEditora {

    void validar(Long id, AtualizarNomeEnderecoDaEditoraDto atualizarNomeEnderecoDaEditoraDto);
}
