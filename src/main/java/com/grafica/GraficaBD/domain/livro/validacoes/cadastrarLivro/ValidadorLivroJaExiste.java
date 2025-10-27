package com.grafica.GraficaBD.domain.livro.validacoes.cadastrarLivro;

import com.grafica.GraficaBD.domain.livro.CadastrarLivroDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorLivroJaExiste implements ValidadorCadastrarLivro {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(CadastrarLivroDto cadastrarLivroDto) {

        if(livroRepository.existsById(cadastrarLivroDto.ISBN())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O livro '" + "' já está cadastrado!");
        }
    }
}
