package com.grafica.GraficaBD.domain.grafica.validacoes.cadastrarGrafica;

import com.grafica.GraficaBD.domain.grafica.CadastrarGraficaDto;
import com.grafica.GraficaBD.domain.grafica.TipoGrafica;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEnderecoPorTipoGraficaCadastrarGrafica implements ValidadorCadastrarGrafica{

    @Override
    public void validar(CadastrarGraficaDto cadastrarGraficaDto) {


        if(cadastrarGraficaDto.tipo_grafica().equals(TipoGrafica.CONTRATADA)){
            if(cadastrarGraficaDto.endereco() == null || cadastrarGraficaDto.endereco().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço deve ser preenchido para gráficas contratadas.");
            }
        }
        if(cadastrarGraficaDto.tipo_grafica().equals(TipoGrafica.PARTICULAR)){
            if(cadastrarGraficaDto.endereco() != null && !cadastrarGraficaDto.endereco().isBlank()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço não deve ser preenchido para gráficas particulares.");
            }
        }
    }
}



