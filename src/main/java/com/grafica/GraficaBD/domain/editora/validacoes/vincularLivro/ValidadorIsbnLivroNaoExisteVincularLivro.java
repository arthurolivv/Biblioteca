package com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorIsbnLivroNaoExisteVincularLivro implements ValidadorVincularLivro{

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto) {

        for(String isbn : livrosPorIsbnDto.isbns()){

            if(!livroRepository.existsById(isbn)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro '" + isbn + "' não está cadastrado!");
            }
        }
    }
}
