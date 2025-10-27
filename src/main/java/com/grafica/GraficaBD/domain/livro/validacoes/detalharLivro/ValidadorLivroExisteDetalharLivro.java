package com.grafica.GraficaBD.domain.livro.validacoes.detalharLivro;

import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorLivroExisteDetalharLivro implements ValidadorDetalharLivro {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(String isbn) {

        if(!livroRepository.existsById(isbn)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro '" + isbn + "' não está cadastrado!");
        }
    }
}
