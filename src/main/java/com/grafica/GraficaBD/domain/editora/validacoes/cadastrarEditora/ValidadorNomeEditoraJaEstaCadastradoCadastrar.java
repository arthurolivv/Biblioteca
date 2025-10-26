package com.grafica.GraficaBD.domain.editora.validacoes.cadastrarEditora;

import com.grafica.GraficaBD.domain.editora.CriarEditoraDto;
import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorNomeEditoraJaEstaCadastradoCadastrar implements ValidadorCadastrarEditora{

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void validar(CriarEditoraDto criarEditoraDto) {

        if(editoraRepository.existsByNome(criarEditoraDto.nome())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A editora com o nome '" + criarEditoraDto.nome() + "' já existe!");
        }
    }

}
