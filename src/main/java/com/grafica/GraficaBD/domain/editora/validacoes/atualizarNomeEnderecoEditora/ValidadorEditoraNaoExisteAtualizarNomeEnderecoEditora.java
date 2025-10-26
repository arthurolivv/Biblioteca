package com.grafica.GraficaBD.domain.editora.validacoes.atualizarNomeEnderecoEditora;

import com.grafica.GraficaBD.domain.editora.Editora;
import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEditoraNaoExisteAtualizarNomeEnderecoEditora {

    @Autowired
    private EditoraRepository editoraRepository;

    public void validar(Long id){
        if(!editoraRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora com id '" + id + "' não está cadastrada.");
        }
    }
}
