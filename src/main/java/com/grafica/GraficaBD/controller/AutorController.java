package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.*;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import com.grafica.GraficaBD.service.EscreveService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EscreveService escreveService;

    @Autowired
    private EscreveRepository escreveRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CriarAutorDto criarAutorDto, UriComponentsBuilder uriBuilder) {

        var novoAutor = new Autor(criarAutorDto);
        autorRepository.save(novoAutor);

        var uri =  uriBuilder.path("/autor/{id}").buildAndExpand(novoAutor.getRg()).toUri();
        //transforma os dados do usuario recem criado para uri
        return ResponseEntity.created(uri).body(novoAutor);
    }

    @PostMapping("/{rg}/livro")
    @Transactional
    public ResponseEntity vincularAutorELivros(@PathVariable String rg, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){

        var dto = escreveService.vincularAutorELivros(rg, livrosPorIsbnDto);
        return ResponseEntity.ok(dto);
    }

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
