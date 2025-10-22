package com.grafica.GraficaBD.controller;

import com.grafica.GraficaBD.domain.grafica.ListarTodaGraficaDto;
import com.grafica.GraficaBD.repository.GraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grafica")
public class GraficaController {

    @Autowired
    private GraficaService graficaService;

    @GetMapping
    public List<ListarTodaGraficaDto> listarTodas(){

        List<ListarTodaGraficaDto> graficas = graficaService.findAll()
                .stream()
                .map(ListarTodaGraficaDto::new)
                .toList();

        return graficas;
    }
}
