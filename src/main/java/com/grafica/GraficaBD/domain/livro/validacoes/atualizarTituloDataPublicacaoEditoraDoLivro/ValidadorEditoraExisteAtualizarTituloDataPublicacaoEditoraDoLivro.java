package com.grafica.GraficaBD.domain.livro.validacoes.atualizarTituloDataPublicacaoEditoraDoLivro;

import com.grafica.GraficaBD.domain.livro.AtualizarTituloDataPublicacaoEditoraDoLivroDto;
import com.grafica.GraficaBD.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEditoraExisteAtualizarTituloDataPublicacaoEditoraDoLivro implements ValidadorAtualizarTituloDataPublicacaoEditoraDoLivro{

    @Autowired
    private EditoraRepository editoraRepository;

    @Override
    public void validar(String isbn, AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivro) {

        if(!editoraRepository.existsById(atualizarTituloDataPublicacaoEditoraDoLivro.editora_id())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora '" + atualizarTituloDataPublicacaoEditoraDoLivro.editora_id() + "' não está cadastrada!");
        }
    }
}
