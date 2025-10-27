package com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores;

import com.grafica.GraficaBD.domain.livro.AutoresPorRgDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorAutorExisteVinculacaoLivroEAutores implements ValidadorVinculacaoLivroEAutores {

    @Autowired
    private AutorRepository  autorRepository;

    @Override
    public void validar(String isbn, AutoresPorRgDto autoresPorRgDto) {

        for(String autor_rg : autoresPorRgDto.autores_rg()){

            if(!autorRepository.existsById(autor_rg)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O autor com rg '" + autor_rg + "' não está cadastrado!");
            }
        }
    }
}
