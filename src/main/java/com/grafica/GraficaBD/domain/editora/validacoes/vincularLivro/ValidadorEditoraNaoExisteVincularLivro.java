package com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEditoraNaoExisteVincularLivro implements ValidadorVincularLivro {

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto) {

        if(!editoraRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora com o id '"+ id + "' não está cadastrada!");
        }
    }
}
