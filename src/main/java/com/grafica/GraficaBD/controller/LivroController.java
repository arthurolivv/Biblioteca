package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.livro.*;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EditoraRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import com.grafica.GraficaBD.service.EditoraService;
import com.grafica.GraficaBD.service.EscreveService;
import com.grafica.GraficaBD.service.LivroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EscreveRepository escreveRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EscreveService escreveService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarLivroDto cadastrarLivroDto) {

        var dto = livroService.cadastrar(cadastrarLivroDto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{isbn}/autor")
    @Transactional
    public ResponseEntity vincularLivroEAutores(@PathVariable String isbn, @RequestBody @Valid AutoresPorRgDto autoresPorRgDto) {

        var dto = escreveService.vincularLivroEAutores(isbn,autoresPorRgDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity listar(){

        var dto = livroService.listar();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity detalhar(@PathVariable String isbn) {

        var dto =  livroService.detalhar(isbn);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{isbn}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable String isbn, @RequestBody @Valid AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivroDto) {

        livroService.atualizar(isbn,atualizarTituloDataPublicacaoEditoraDoLivroDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{isbn}/autor")
    @Transactional
    public ResponseEntity desvincularLivroEAutores(@PathVariable String isbn, @RequestBody @Valid AutoresPorRgDto autoresPorRgDto) {

        escreveService.desvincularLivroEAutores(isbn,autoresPorRgDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{isbn}")
    @Transactional
    public ResponseEntity deletar(@PathVariable String isbn) {

        livroService.deletar(isbn);
        return ResponseEntity.noContent().build();
    }


}
