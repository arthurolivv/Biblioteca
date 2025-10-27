package com.grafica.GraficaBD.domain.contrato.validacoes.adicionarContrato;

import com.grafica.GraficaBD.domain.grafica.AdicionarContratoNaGraficaDto;

public interface ValidadorAdicionarContrato {

    void validar(Long id, AdicionarContratoNaGraficaDto adicionarContratoNaGraficaDto);
}
