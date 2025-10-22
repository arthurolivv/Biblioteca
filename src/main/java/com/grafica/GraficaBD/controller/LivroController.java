package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.livro.*;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
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
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EscreveRepository escreveRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarLivroDto cadastrarLivroDto) {

        var novoLivro = new Livro(cadastrarLivroDto);

        if (cadastrarLivroDto.autores_rg() != null) {
            for (String autorRg : cadastrarLivroDto.autores_rg()) {

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
    public DetalharLivroDto detalhar(@PathVariable String isbn) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        List<DetalharNomeRgAutorDto> autores = livro.getEscreve()
                .stream()
                .map(Escreve::getAutor)
                .map(DetalharNomeRgAutorDto::new)
                .toList();

        return new DetalharLivroDto(livro, autores);

    }

    @PutMapping("/{isbn}")
    @Transactional
    public void atualizar(@PathVariable String isbn, @RequestBody @Valid AtualizarTituloDataPublicacaoEditoraLivroDto atualizarTituloDataPublicacaoEditoraLivroDto) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if(atualizarTituloDataPublicacaoEditoraLivroDto != null && !atualizarTituloDataPublicacaoEditoraLivroDto.titulo().isBlank()){
            livro.setTitulo(atualizarTituloDataPublicacaoEditoraLivroDto.titulo());
        }
        if(atualizarTituloDataPublicacaoEditoraLivroDto.data_de_publicacao() != null && !atualizarTituloDataPublicacaoEditoraLivroDto.data_de_publicacao().toString().isBlank()){
            livro.setData_de_publicacao(atualizarTituloDataPublicacaoEditoraLivroDto.data_de_publicacao());
        }
        if(atualizarTituloDataPublicacaoEditoraLivroDto.addAutorRg() != null && !atualizarTituloDataPublicacaoEditoraLivroDto.addAutorRg().isEmpty()){
            for(String autorRg : atualizarTituloDataPublicacaoEditoraLivroDto.addAutorRg()){

                var autor = autorRepository.findById(autorRg)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

                var escreve = new Escreve(new EscreveId(livro.getIsbn(), autorRg),
                        livro,
                        autor);

                livro.getEscreve().add(escreve);
            }
        }
        if(atualizarTituloDataPublicacaoEditoraLivroDto.remAutorRg() != null && !atualizarTituloDataPublicacaoEditoraLivroDto.remAutorRg().isEmpty()){
            for(String autorRg : atualizarTituloDataPublicacaoEditoraLivroDto.remAutorRg()){

                var escreve = escreveRepository.findById(new EscreveId(livro.getIsbn(), autorRg))
                        .orElseThrow(() -> new RuntimeException("Relação entre livro e autor não encontrada!"));

                escreve.setLivro(null);
                livro.getEscreve().remove(escreve);
            }
        }
        livroRepository.save(livro);
    }

    @DeleteMapping("/{isbn}")
    @Transactional
    public void remover(@PathVariable String isbn) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(()->new RuntimeException("Livro não Encontrado!"));

        livroRepository.delete(livro);
    }


}
