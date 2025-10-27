package com.grafica.GraficaBD.domain.grafica.validacoes.deletarGrafica;

import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorGraficaExisteDeletarGrafica implements ValidadorDeletarGrafica{

    @Autowired
    private GraficaRepository graficaRepository;

    @Override
    public void validar(Long id) {
        if(!graficaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "A editora '" + id + "' não está cadastrada!");
        }
    }
}
