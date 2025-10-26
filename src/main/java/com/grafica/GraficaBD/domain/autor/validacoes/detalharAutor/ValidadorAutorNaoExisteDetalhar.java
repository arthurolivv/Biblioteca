package com.grafica.GraficaBD.domain.autor.validacoes.detalharAutor;

import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorAutorNaoExisteDetalhar implements ValidadorDetalharAutor {

    @Autowired
    AutorRepository autorRepository;

    @Override
    public void validar(String rg) {

        if(!autorRepository.existsById(rg)){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "O autor '" + rg +"' não está cadastrado!");
        }
    }
}
