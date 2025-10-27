package com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores;

import com.grafica.GraficaBD.domain.livro.AutoresPorRgDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorLivroExisteVinculacaoLivroEAutores implements ValidadorVinculacaoLivroEAutores {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(String isbn, AutoresPorRgDto autoresPorRgDto) {

        if(!livroRepository.existsById(isbn)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro '" + isbn + "' não está cadastrado!");
        }
    }
}
