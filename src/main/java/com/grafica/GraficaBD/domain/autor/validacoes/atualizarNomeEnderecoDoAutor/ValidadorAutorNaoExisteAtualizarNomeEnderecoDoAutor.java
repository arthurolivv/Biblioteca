package com.grafica.GraficaBD.domain.autor.validacoes.atualizarNomeEnderecoDoAutor;

import com.grafica.GraficaBD.domain.autor.AtualizarNomeEnderecoDoAutorDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorAutorNaoExisteAtualizarNomeEnderecoDoAutor implements ValidadorAtualizarNomeEnderecoDoAutor {

    @Autowired
    private AutorRepository autorRepository;
    @Override
    public void validar(String rg, AtualizarNomeEnderecoDoAutorDto atualizarNomeEnderecoDoAutorDto) {

        if(!autorRepository.existsById(rg)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O autor '" + rg +"' não está cadastrado!");
        }
    }
}
