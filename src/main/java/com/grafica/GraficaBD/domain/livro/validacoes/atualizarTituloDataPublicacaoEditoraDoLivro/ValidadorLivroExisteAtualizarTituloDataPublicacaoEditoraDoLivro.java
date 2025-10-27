package com.grafica.GraficaBD.domain.livro.validacoes.atualizarTituloDataPublicacaoEditoraDoLivro;

import com.grafica.GraficaBD.domain.livro.AtualizarTituloDataPublicacaoEditoraDoLivroDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorLivroExisteAtualizarTituloDataPublicacaoEditoraDoLivro implements  ValidadorAtualizarTituloDataPublicacaoEditoraDoLivro {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(String isbn, AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivro) {

        if(!livroRepository.existsById(isbn)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro '" + isbn + "' não está cadastrado!");
        }
    }
}
