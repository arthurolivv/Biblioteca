package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.*;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EscreveRepository escreveRepository;

    @GetMapping
    public List<ListarAutorDto> listar() {

        List<ListarAutorDto> autores = autorRepository.findAll()
                .stream()
                .map(ListarAutorDto::new)
                .toList();

        return autores;
    }

    @GetMapping("{rg}")
    public DetalharAutorDto detalhar(@PathVariable String rg) {

        var autor = autorRepository.getReferenceById(rg);

        List<DetalharLivroSemAutorDto> livros = autor.getEscreve()
                .stream()
                .map(Escreve::getLivro)
                .map(DetalharLivroSemAutorDto::new) //converte pra dto
                .toList();

        return new DetalharAutorDto(autor.getNome(), autor.getEndereco(), livros);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CriarAutorDto criarAutorDto) {

        var novoAutor = new Autor(criarAutorDto);
        autorRepository.save(novoAutor);
    }

    @PutMapping("/{rg}")
    @Transactional
    public void atualizar(@PathVariable String rg, @RequestBody @Valid AtualizarNomeEnderecoDoAutorDto atualizarNomeEnderecoAutorLivroDto){

        Autor autor = autorRepository.findById(rg)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        if (atualizarNomeEnderecoAutorLivroDto.nome() != null && !atualizarNomeEnderecoAutorLivroDto.nome().isBlank()) {
            autor.setNome(atualizarNomeEnderecoAutorLivroDto.nome());
        }

        if (atualizarNomeEnderecoAutorLivroDto.endereco() != null && !atualizarNomeEnderecoAutorLivroDto.endereco().isBlank()) {
            autor.setEndereco(atualizarNomeEnderecoAutorLivroDto.endereco());
        }
        autorRepository.save(autor);
    }

    @PostMapping("/{rg}/livro")
    @Transactional
    public void adicionarLivro(@PathVariable String rg, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        var autor = autorRepository.findById(rg)
                .orElseThrow(()->new RuntimeException("Autor não encontrado!"));

        for(String isbn : livrosPorIsbnDto.isbns()){

            var livro = livroRepository.findById(isbn)
                        .orElseThrow(() -> new RuntimeException("Livro não Encontrado"));

            var escreve = new Escreve(new EscreveId(isbn, autor.getRg()),
                        livro,
                        autor);

            autor.getEscreve().add(escreve);
        }

        autorRepository.save(autor);

    }

    @DeleteMapping("/{rg}/livro")
    @Transactional
    public void deletarLivro(@PathVariable String rg, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        var autor = autorRepository.findById(rg)
                .orElseThrow(()->new RuntimeException("Autor não encontrado!"));

        if(livrosPorIsbnDto.isbns() != null && !livrosPorIsbnDto.isbns().isEmpty()) {

            for(String isbn : livrosPorIsbnDto.isbns()){

                var escreve = escreveRepository.findById(new EscreveId(isbn, autor.getRg()))
                        .orElseThrow(() -> new RuntimeException("Relação entre autor e livro não encontrada"));

                escreve.setAutor(null);
                autor.getEscreve().remove(escreve);
            }
        }

        autorRepository.save(autor);

    }

    @DeleteMapping("/{rg}")
    @Transactional
    public void deletar(@PathVariable String rg) {

        var autor = autorRepository.findById(rg)
                .orElseThrow(()->new RuntimeException("Autor não Encontrado!"));


        autorRepository.delete(autor);
    }
}
