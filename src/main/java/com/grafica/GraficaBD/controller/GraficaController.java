package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.contrato.Contrato;
import com.grafica.GraficaBD.domain.contrato.DetalharContratosDeGraficaContradaDto;
import com.grafica.GraficaBD.domain.grafica.*;
import com.grafica.GraficaBD.repository.ContratoRepository;
import com.grafica.GraficaBD.repository.GraficaRepository;
import com.grafica.GraficaBD.service.ContratoService;
import com.grafica.GraficaBD.service.GraficaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/grafica")
public class GraficaController {

    @Autowired
    private GraficaService graficaService;

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarGraficaDto cadastrarGraficaDto, UriComponentsBuilder uriBuilder){

        var dto = graficaService.cadastrar(cadastrarGraficaDto);
        var uri = uriBuilder.path("/grafica/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/{id}/contrato")
    @Transactional
    public ResponseEntity adicionarContrato(@PathVariable Long id, @RequestBody @Valid AdicionarContratoNaGraficaDto adicionarContratoNaGraficaDto,
    UriComponentsBuilder uriBuilder){
        var dto =contratoService.adicionarContrato(id, adicionarContratoNaGraficaDto);
        var uri = uriBuilder.path("/grafica/{id}/contrato/{id}").buildAndExpand(id, dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity listar(){

        var dto = graficaService.listar();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/detalhar")
    public ResponseEntity detalhar(@PathVariable Long id){

        var dto = graficaService.detalhar(id);
        return ResponseEntity.ok(dto);

    }

//    @DeleteMapping("/{id}/contrato/{id}")
//    @Transactional
//    public ResponseEntity deletarContrato(@PathVariable Long idGrafica, @PathVariable Long idContrato){
//
//
//    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){

        graficaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
