package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.grafica.*;
import com.grafica.GraficaBD.domain.imprime.Imprime;
import com.grafica.GraficaBD.domain.imprime.ImprimeId;
import com.grafica.GraficaBD.repository.GraficaService;
import com.grafica.GraficaBD.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grafica")
public class GraficaController {

    @Autowired
    private GraficaService graficaService;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<ListarTodaGraficaDto> listarTodas(){

        List<ListarTodaGraficaDto> graficas = graficaService.findAll()
                .stream()
                .map(ListarTodaGraficaDto::new)
                .toList();

        return graficas;
    }

    @PostMapping
    @Transactional
    public void cadastrar(CadastrarGraficaDto cadastrarGraficaDto){

        if(cadastrarGraficaDto.tipoGrafica().equals(TipoGrafica.PARTICULAR)){

        }

        if(cadastrarGraficaDto.tipoGrafica().equals(TipoGrafica.CONTRATADA)){

        }


        graficaService.save(novaGrafica);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody AtualizarNomeTipoGraficaDto atualizarNomeTipoGraficaDto){

        var grafica = graficaService.findById(id);
    }
}
