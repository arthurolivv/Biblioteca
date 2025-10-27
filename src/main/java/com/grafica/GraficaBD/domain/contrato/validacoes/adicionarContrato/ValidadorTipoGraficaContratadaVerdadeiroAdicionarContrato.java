package com.grafica.GraficaBD.domain.contrato.validacoes.adicionarContrato;

import com.grafica.GraficaBD.domain.grafica.AdicionarContratoNaGraficaDto;
import com.grafica.GraficaBD.domain.grafica.GraficaContratada;
import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorTipoGraficaContratadaVerdadeiroAdicionarContrato implements ValidadorAdicionarContrato{

    @Autowired
    private GraficaRepository graficaRepository;

    @Autowired
    private ValidadorGraficaExisteAdicionarContrato validadorGraficaExisteAdicionarContrato;

    @Override
    public void validar(Long id, AdicionarContratoNaGraficaDto adicionarContratoNaGraficaDto) {

        validadorGraficaExisteAdicionarContrato.validar(id, adicionarContratoNaGraficaDto);

        var grafica = graficaRepository.findById(id).get();

        if (!(grafica instanceof GraficaContratada)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A gráfica: '" + grafica.getNome() + "' id: '" + id + "' não pertence ao tipo 'CONTRATADA'!");
        }

    }

}
