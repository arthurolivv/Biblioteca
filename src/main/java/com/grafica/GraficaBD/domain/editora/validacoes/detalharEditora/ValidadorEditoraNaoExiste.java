package com.grafica.GraficaBD.domain.editora.validacoes.detalharEditora;

import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEditoraNaoExiste implements ValidadorDetalharEditora {

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void validar(Long id) {

        if(!editoraRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora com o id '" + id + "' não está cadastrada!");
        }
    }
}
