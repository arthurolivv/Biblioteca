package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.*;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.livro.ListarLivroDto;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        List<ListarLivroDto> livros = autor.getEscreve()
                .stream()
                .map(Escreve::getLivro)
                .map(ListarLivroDto::new) //converte pra dto
                .toList();

        return new DetalharAutorDto(autor.getNome(), autor.getEndereco(), livros);
    }

    @PostMapping
    @Transactional
    public void adicionar(@RequestBody @Valid CriarAutorDto criarAutorDto) {

        var autor = new Autor(criarAutorDto);

        if (autor.getEscreve() == null) {
            autor.setEscreve(new ArrayList<>());
        }

        if(criarAutorDto.livrosIsbn() != null) {
            for(String isbn : criarAutorDto.livrosIsbn()) {
                System.out.println("ISBN recebido: '" + isbn + "'");

                var livro = livroRepository.findById(isbn)
                        .orElseThrow(() -> new RuntimeException("Livro não Encontrado"));

                var escreve = new Escreve(new EscreveId(isbn, autor.getRg()),
                        livro,
                        autor);

                autor.getEscreve().add(escreve);
            }
        }

        autorRepository.save(autor);

    }

    @PutMapping("/{rg}")
    @Transactional
    public void atualizarEndereco(@PathVariable String rg, @RequestBody @Valid AtualizarNomeEnderecoAutorDto atualizarNomeEnderecoAutorDto){

        Autor autor = autorRepository.findById(rg)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        if (atualizarNomeEnderecoAutorDto.nome() != null && !atualizarNomeEnderecoAutorDto.nome().isBlank()) {
            autor.setNome(atualizarNomeEnderecoAutorDto.nome());
        }

        if (atualizarNomeEnderecoAutorDto.endereco() != null && !atualizarNomeEnderecoAutorDto.endereco().isBlank()) {
            autor.setEndereco(atualizarNomeEnderecoAutorDto.endereco());
        }

        autorRepository.save(autor);
    }

    @DeleteMapping("{rg}")
    @Transactional
    public void adicionar(@PathVariable String rg) {

        var autor = autorRepository.getReferenceById(rg);
        autorRepository.delete(autor);
    }
}
