package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.livro.ListarLivroDto;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.domain.livro.LivroDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid LivroDto livroDto) {

        var novoLivro = new Livro(livroDto);
        livroRepository.save(novoLivro);
    }

    @GetMapping
    public List<ListarLivroDto> listar(){

        List<ListarLivroDto> livros = livroRepository.findAll()
                .stream()
                .map(ListarLivroDto::new)
                .toList();

        return livros;
    }

    @GetMapping("/{isbn}")
    public ListarLivroDto detalhar(@PathVariable String isbn) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        return new ListarLivroDto(livro);

    }

    @DeleteMapping("/{isbn}")
    @Transactional
    public void remover(@PathVariable String isbn) {

        livroRepository.deleteById(isbn);
    }
}
