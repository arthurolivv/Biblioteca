package com.grafica.GraficaBD.domain.contrato.validacoes.adicionarContrato;

import com.grafica.GraficaBD.domain.grafica.AdicionarContratoNaGraficaDto;
import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorGraficaExisteAdicionarContrato implements ValidadorAdicionarContrato{

    @Autowired
    private GraficaRepository graficaRepository;

    @Override
    public void validar(Long id, AdicionarContratoNaGraficaDto adicionarContratoNaGraficaDto) {

        if(!graficaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A gráfica '" + id + "' não está cadastrada!");
        }
    }
}
