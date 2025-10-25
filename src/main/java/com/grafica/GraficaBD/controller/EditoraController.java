package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.editora.*;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.EditoraRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
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

    @PostMapping("/{id}/livro")
    @Transactional
    public void adicionarLivro(@PathVariable Long id, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        var editora = editoraRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Editora não encontrada!"));

        for (String isbn : livrosPorIsbnDto.isbns()){

            var novoLivro = livroRepository.findById(isbn)
                    .orElseThrow(()->new RuntimeException("Livro não encontrado!"));

            novoLivro.setEditora(editora);
            editora.getLivros().add(novoLivro);
        }

        editoraRepository.save(editora);
    }

    @DeleteMapping("/{id}/livro")
    @Transactional
    public void deletarLivro(@PathVariable Long id, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        var editora = editoraRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Editora não encontrada!"));

        for (String isbn : livrosPorIsbnDto.isbns()){

            var livro = livroRepository.findById(isbn)
                    .orElseThrow(()->new RuntimeException("Livro não encontrado!"));

            if(!livro.getEditora().getId().equals(id)){
                throw new RuntimeException("Livro '" + isbn + "' não pertence a editora '" + id + "'");
            }

            livro.setEditora(null);
            editora.getLivros().remove(livro);
        }

        editoraRepository.save(editora);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarNomeEnderecoDaEditoraDto atualizarNomeEnderecoDaEditoraDto) {

        var editora = editoraRepository.getReferenceById(id);

        if (atualizarNomeEnderecoDaEditoraDto.nome() != null && !atualizarNomeEnderecoDaEditoraDto.nome().isBlank()) {
            editora.setNome(atualizarNomeEnderecoDaEditoraDto.nome());
        }
        if (atualizarNomeEnderecoDaEditoraDto.endereco() != null && !atualizarNomeEnderecoDaEditoraDto.endereco().isBlank()) {
            editora.setEndereco(atualizarNomeEnderecoDaEditoraDto.endereco());
        }

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

        List<ListarEditoraDto> editoras = editoraRepository.findAll()
                .stream()
                .map(ListarEditoraDto::new)
                .toList();

        return editoras;
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
