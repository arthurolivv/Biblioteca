package com.biblioteca.BibliotecaBD.controller;

import com.biblioteca.BibliotecaBD.domain.editora.*;
import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import com.biblioteca.BibliotecaBD.domain.livro.LivroDto;
import com.biblioteca.BibliotecaBD.domain.livro.LivroIsbnDto;
import com.biblioteca.BibliotecaBD.repository.EditoraRepository;
import com.biblioteca.BibliotecaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid EditoraDto editoraDto){

        var editora =  new Editora(editoraDto);
        editoraRepository.save(editora);
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public void adicionarLivro(@PathVariable Long id, @RequestBody @Valid LivroIsbnDto livroIsbnDto){
//
//        var editora = editoraRepository.getReferenceById(id);
//
//        var livro = livroRepository.findById(livroIsbnDto.ISBN())
//                .orElseThrow(() -> new RuntimeException("Livro Não Encontrado!"));
//
//        livro.setEditora(editora);
//
//        livroRepository.save(livro);
//    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizarEndereco(@PathVariable Long id, @RequestBody @Valid AtualizarEnderecoEditoraDto atualizarEnderecoEditoraDto){

        var editora = editoraRepository.getReferenceById(id);

        editora.setEndereco(atualizarEnderecoEditoraDto.endereco());

        editoraRepository.save(editora);
    }

    @GetMapping("/{id}")
    public DetalharEditoraDto detalhar(@PathVariable Long id) {

        var editora = editoraRepository.getReferenceById(id);

        List<String> livroTitulo = editora.getLivros()
                .stream()
                .map(Livro::getTitulo)
                .toList();

        return new DetalharEditoraDto(editora.getId(), editora.getNome(), editora.getEndereco(), livroTitulo);

    }

    @GetMapping
    public List<ListarEditoraDto> listar() {
        return editoraRepository.findAll()
                .stream()
                .map(ListarEditoraDto::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        //o certo seria desativar para false mas como o script nao nos fornece
        //a opçao de editora ativa ou não teve que ser deletar

        //a deleção so ocorre quando seus livros vinculados forem apagados do catalogo
        editoraRepository.deleteById(id);
    }
}
