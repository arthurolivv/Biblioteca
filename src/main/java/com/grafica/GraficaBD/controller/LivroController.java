package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.livro.CriarLivroDto;
import com.grafica.GraficaBD.domain.livro.ListarLivroDto;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CriarLivroDto criarLivroDto) {

        var novoLivro = new Livro(criarLivroDto);

        if (criarLivroDto.autores_rg() != null) {
            for (String autorRg : criarLivroDto.autores_rg()) {

                var autor = autorRepository.findById(autorRg)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

                var escreve = new Escreve(new EscreveId(novoLivro.getIsbn(), autorRg),
                        novoLivro,
                        autor);

                novoLivro.getEscreve().add(escreve);
            }
        }
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
