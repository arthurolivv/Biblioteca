package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.editora.*;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.EditoraRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import com.grafica.GraficaBD.service.EditoraService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EditoraService editoraService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CriarEditoraDto criarEditoraDto, UriComponentsBuilder uriBuilder) {

        var dto = editoraService.cadastrar(criarEditoraDto);
        var uri = uriBuilder.path("/editora/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/{id}/livro")
    @Transactional
    public ResponseEntity vincularLivro(@PathVariable Long id, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){
        var dto = editoraService.vincularLivro(id, livrosPorIsbnDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar() {

        var dto = editoraService.listar();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var dto = editoraService.detalhar(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarNomeEnderecoDaEditoraDto atualizarNomeEnderecoDaEditoraDto) {

        editoraService.atualizar(id, atualizarNomeEnderecoDaEditoraDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/livro")
    @Transactional
    public ResponseEntity deletarLivro(@PathVariable Long id, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        editoraService.desvicularLivro(id, livrosPorIsbnDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        //o certo seria desativar para false mas como o script nao nos fornece
        //a opçao de editora ativa ou não teve que ser deletar

        //a deleção so ocorre quando seus livros vinculados forem apagados do catalogo
        editoraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
