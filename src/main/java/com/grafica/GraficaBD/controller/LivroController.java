package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.livro.*;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EditoraRepository;
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
    @Autowired
    private EditoraRepository editoraRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarLivroDto cadastrarLivroDto) {

        var novoLivro = new Livro(cadastrarLivroDto);

        livroRepository.save(novoLivro);
    }

    @PostMapping("/{isbn}/autor")
    @Transactional
    public void adicionarAutor(@PathVariable String isbn, @RequestBody @Valid AutoresPorRgDto autoresPorRgDto) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(()-> new RuntimeException("Livro não encontrado!"));

            for (String autorRg : autoresPorRgDto.autores_rg()) {

                var autor = autorRepository.findById(autorRg)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

                var escreve = new Escreve(new EscreveId(livro.getIsbn(), autorRg),
                        livro,
                        autor);

                livro.getEscreve().add(escreve);
            }

        livroRepository.save(livro);
    }

    @DeleteMapping("/{isbn}/autor")
    @Transactional
    public void deletarAutor(@PathVariable String isbn, @RequestBody @Valid AutoresPorRgDto autoresPorRgDto) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(()-> new RuntimeException("Livro não encontrado!"));

        for(String autorRg : autoresPorRgDto.autores_rg()){

            var escreve = escreveRepository.findById(new EscreveId(livro.getIsbn(), autorRg))
                    .orElseThrow(() -> new RuntimeException("Relação entre livro e autor não encontrada!"));

            escreve.setLivro(null);
            livro.getEscreve().remove(escreve);
        }

        livroRepository.save(livro);
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
    public void atualizar(@PathVariable String isbn, @RequestBody @Valid AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivroDto) {

        var livro = livroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if(atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo() != null && !atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo().isBlank()){
            livro.setTitulo(atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo());
        }
        if(atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao() != null && !atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao().toString().isBlank()){
            livro.setData_de_publicacao(atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao());
        }
        livro.setEditora(
                atualizarTituloDataPublicacaoEditoraDoLivroDto.editora_id() != null
                        ? editoraRepository.findById(atualizarTituloDataPublicacaoEditoraDoLivroDto.editora_id())
                        .orElseThrow(() -> new RuntimeException("Editora não encontrada!"))
                        : null
        );

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
