package com.grafica.GraficaBD.domain.grafica.validacoes.cadastrarGrafica;

import com.grafica.GraficaBD.domain.grafica.CadastrarGraficaDto;
import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorGraficaNaoExisteCadastrarGrafica implements ValidadorCadastrarGrafica{

    @Autowired
    private GraficaRepository graficaRepository;

    @Override
    public void validar(CadastrarGraficaDto cadastrarGraficaDto) {

        if(graficaRepository.existsByNome(cadastrarGraficaDto.nome())){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "A gráfica com o nome '" + cadastrarGraficaDto.nome() + "' já está cadastrada!");
        }
    }
}
