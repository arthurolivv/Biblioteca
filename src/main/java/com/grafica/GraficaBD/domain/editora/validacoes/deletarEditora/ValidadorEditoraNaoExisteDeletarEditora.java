package com.grafica.GraficaBD.domain.editora.validacoes.deletarEditora;

import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEditoraNaoExisteDeletarEditora implements ValidadorDeletarEditora {

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void validar(Long id) {

        if (!editoraRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora '" + id + "' não está cadastrada!");
        }
    }
}
