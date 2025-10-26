package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.autor.*;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import com.grafica.GraficaBD.service.AutorService;
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
    private AutorService autorService;

    @Autowired
    private EscreveService escreveService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CriarAutorDto criarAutorDto, UriComponentsBuilder uriBuilder) {

        var dto = autorService.cadastrar(criarAutorDto);
        var uri =  uriBuilder.path("/autor/{rg}").buildAndExpand(criarAutorDto.RG()).toUri();
        //transforma os dados do usuario recem criado para uri
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/{rg}/livro")
    @Transactional
    public ResponseEntity vincularAutorELivros(@PathVariable String rg, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto, UriComponentsBuilder uriBuilder) {

        var dto = escreveService.vincularAutorELivros(rg, livrosPorIsbnDto);

        // Montando a URI do recurso do autor atualizado
        var location = uriBuilder
                .path("/autor/{rg}")       // caminho do recurso principal (autor)
                .buildAndExpand(rg)        // substitui {rg} pelo RG do autor
                .toUri();

        // Retorna 201 Created + Location header + DTO no corpo
        return ResponseEntity
                .created(location)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity listar() {
        var lista = autorService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{rg}")
    public ResponseEntity detalhar(@PathVariable String rg) {

        var autor = autorService.detalhar(rg);
        return ResponseEntity.ok(autor);
    }

    @PutMapping("/{rg}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable String rg, @RequestBody @Valid AtualizarNomeEnderecoDoAutorDto atualizarNomeEnderecoAutorLivroDto){

        autorService.atualizar(rg, atualizarNomeEnderecoAutorLivroDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{rg}/livro")
    @Transactional
    public ResponseEntity desvincularAutorELivros(@PathVariable String rg, @RequestBody @Valid LivrosPorIsbnDto livrosPorIsbnDto){
        escreveService.desvincularAutorELivros(rg, livrosPorIsbnDto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{rg}")
    @Transactional
    public ResponseEntity deletar(@PathVariable String rg) {

        autorService.deletar(rg);
        return ResponseEntity.noContent().build();
    }
}
