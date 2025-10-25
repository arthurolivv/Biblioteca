package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.grafica.*;
import com.grafica.GraficaBD.repository.ContratoRepository;
import com.grafica.GraficaBD.repository.GraficaRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/grafica")
public class GraficaController {

    @Autowired
    private GraficaRepository graficaRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @GetMapping
    public List<ListarTodaGraficaDto> listarTodas(){

        List<ListarTodaGraficaDto> graficas = graficaRepository.findAll()
                .stream()
                .map(ListarTodaGraficaDto::new)
                .toList();

        return graficas;
    }

    @GetMapping("/{id}/detalhar")
    public DetalharGraficaDto detalhar(@PathVariable Long id){

        var grafica = graficaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gráfica Não Encontrada!"));

        List<DetalharImpressoesGraficaDto> impressoes = grafica.getImprime()
                .stream()
                .map(imprime -> new DetalharImpressoesGraficaDto(
                        grafica.getNome(),
                        imprime.getLivro().getIsbn(),
                        imprime.getLivro().getTitulo(),
                        imprime.getNto_copias(),
                        imprime.getData_entrega()
                ))
                .toList();

//        List<DetalharContratosDto> contratos;
//
//        if(grafica instanceof GraficaContratada graficaContratada){
//            contratos = graficaContratada.getContrato()
//                    .stream()
//                    .map(contrato -> new DetalharContratosDto(
//                            contrato.getId(),
//                            contrato.getValor(),
//                            contrato.getNome_responsavel()
//                    ))
//                    .toList();
//        }
//        else {
//                contratos = List.of();
//        }

        List<DetalharContratosDto> contratos = (grafica instanceof GraficaContratada gc)
                ? gc.getContrato()
                .stream()
                .map(c -> new DetalharContratosDto(c.getId(), c.getValor(), c.getNome_responsavel()))
                .toList()
                : List.of(); // caso seja Particular

        return new DetalharGraficaDto(grafica, impressoes, contratos);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarGraficaDto cadastrarGraficaDto){

        Grafica novaGrafica = null;

        if(cadastrarGraficaDto.tipo_grafica().equals(TipoGrafica.PARTICULAR)){
            if(cadastrarGraficaDto.endereco() != null && !cadastrarGraficaDto.endereco().isBlank()){
                throw new IllegalArgumentException("Endereço não deve ser preenchido para gráficas particulares.");
            }
            novaGrafica = new GraficaParticular(cadastrarGraficaDto);
        }

        else if(cadastrarGraficaDto.tipo_grafica().equals(TipoGrafica.CONTRATADA)){
            if(cadastrarGraficaDto.endereco() == null || cadastrarGraficaDto.endereco().isBlank()){
                throw new IllegalArgumentException("Endereço deve ser preenchido para gráficas contratadas.");
            }
                novaGrafica = new GraficaContratada(cadastrarGraficaDto);
        }
        graficaRepository.save(novaGrafica);
    }

    @PostMapping("/{id}/adicionar_contrato")
    @Transactional
    public void adicionarContrato(@PathVariable Long id, @RequestBody @Valid adicionarContratoNaGraficaDto adicionarContratoNaGraficaDto){

        var grafica = graficaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Gráfica Não Encontrada!"));

        if(grafica instanceof GraficaParticular){
            throw new IllegalArgumentException("Não é possível adicionar contratos para gráficas particulares.");
        }

        GraficaContratada graficaContratada = (GraficaContratada) grafica;

        var contrato = new Contrato(adicionarContratoNaGraficaDto, graficaContratada);

        graficaContratada.getContrato().add(contrato);

        contratoRepository.save(contrato);
    }
}
