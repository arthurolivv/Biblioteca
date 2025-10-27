package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.contrato.Contrato;
import com.grafica.GraficaBD.domain.contrato.DetalharNovoContratoDto;
import com.grafica.GraficaBD.domain.contrato.validacoes.adicionarContrato.ValidadorAdicionarContrato;
import com.grafica.GraficaBD.domain.grafica.AdicionarContratoNaGraficaDto;
import com.grafica.GraficaBD.domain.grafica.GraficaContratada;
import com.grafica.GraficaBD.repository.ContratoRepository;
import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private GraficaRepository graficaRepository;

    @Autowired
    private List<ValidadorAdicionarContrato> validadoresAdicionarContrato;

    public DetalharNovoContratoDto adicionarContrato(Long id, AdicionarContratoNaGraficaDto adicionarContratoNaGraficaDto){

        validadoresAdicionarContrato.stream()
                .forEach(v -> v.validar(id, adicionarContratoNaGraficaDto));

        GraficaContratada gc = (GraficaContratada) graficaRepository.getReferenceById(id);

        var contrato = new Contrato(adicionarContratoNaGraficaDto, gc);

        gc.getContrato().add(contrato);

        contratoRepository.save(contrato);

        return new DetalharNovoContratoDto(contrato);
    }

    //deletarContrato

    public List<ListarTodosOsContratosDto> detalhar(){

        List<Contrato> contratos = StreamSupport.stream(contratoRepository.findAll().spliterator(), false)
                .toList();

        List<ListarTodosOsContratosDto> detalharContratos = contratos.stream()
                .map(ListarTodosOsContratosDto::new)
                .toList();

        return detalharContratos;
    }
}
