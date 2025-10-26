package com.grafica.GraficaBD.domain.autor.validacoes.cadastrarAutor;

import com.grafica.GraficaBD.domain.autor.CriarAutorDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorAutorJaExisteCadastrar implements ValidadorCadastrarAutor {

    @Autowired
    AutorRepository autorRepository;

    @Override
    public void validar(CriarAutorDto criarAutorDto) {

        if(autorRepository.existsById(criarAutorDto.RG())){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "O autor '" + criarAutorDto.RG() +"' já está cadastrado!");
        }
    }
}
