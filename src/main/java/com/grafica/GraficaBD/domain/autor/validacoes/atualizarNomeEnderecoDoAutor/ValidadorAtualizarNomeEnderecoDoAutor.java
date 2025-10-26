package com.grafica.GraficaBD.domain.autor.validacoes.atualizarNomeEnderecoDoAutor;

import com.grafica.GraficaBD.domain.autor.AtualizarNomeEnderecoDoAutorDto;

public interface ValidadorAtualizarNomeEnderecoDoAutor {

    void validar(String rg, AtualizarNomeEnderecoDoAutorDto atualizarNomeEnderecoDoAutorDto);
}
