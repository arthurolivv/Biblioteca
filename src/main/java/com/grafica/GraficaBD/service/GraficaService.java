package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.contrato.Contrato;
import com.grafica.GraficaBD.domain.contrato.DetalharContratosDeGraficaContradaDto;
import com.grafica.GraficaBD.domain.contrato.validacoes.adicionarContrato.ValidadorAdicionarContrato;
import com.grafica.GraficaBD.domain.grafica.*;
import com.grafica.GraficaBD.domain.grafica.validacoes.cadastrarGrafica.ValidadorCadastrarGrafica;
import com.grafica.GraficaBD.domain.grafica.validacoes.deletarGrafica.ValidadorDeletarGrafica;
import com.grafica.GraficaBD.domain.grafica.validacoes.detalharGrafica.ValidadorDetalharGrafica;
import com.grafica.GraficaBD.repository.GraficaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraficaService {

    @Autowired
    private GraficaRepository graficaRepository;

    @Autowired
    private List<ValidadorCadastrarGrafica> validadoresCadastrarGrafica;

    @Autowired
    private List<ValidadorDetalharGrafica> validadoresDetalharGrafica;

    @Autowired
    private List<ValidadorDeletarGrafica> validadoresDeletarGrafica;

    public DetalharNovaGraficaDto cadastrar(CadastrarGraficaDto cadastrarGraficaDto){

        validadoresCadastrarGrafica.stream()
                .forEach(validador -> validador.validar(cadastrarGraficaDto));

        Grafica novaGrafica = switch (cadastrarGraficaDto.tipo_grafica()){ //seleciona o que vai variar
            // nessa situaçao de cases

            case PARTICULAR -> new GraficaParticular(cadastrarGraficaDto);
            case CONTRATADA ->  new GraficaContratada(cadastrarGraficaDto);
        };

        graficaRepository.save(novaGrafica);
        return new DetalharNovaGraficaDto(novaGrafica);

    }

    public List<ListarTodaGraficaDto> listar(){

        List<ListarTodaGraficaDto> graficas = graficaRepository.findAll()
                .stream()
                .map(ListarTodaGraficaDto::new)
                .toList();

        return graficas;
    }

    public DetalharGraficaDto detalhar(Long id){

        validadoresDetalharGrafica.stream()
                .forEach(v -> v.validar(id));

        var grafica = graficaRepository.getReferenceById(id);

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

        // caso seja GraficaParticular
        List<DetalharContratosDeGraficaContradaDto> contratos = (grafica instanceof GraficaContratada gc)
                ? gc.getContrato()
                .stream()
                .map(c -> new DetalharContratosDeGraficaContradaDto(c.getId(), c.getValor(), c.getNome_responsavel()))
                .toList()
                : List.of();

        return new DetalharGraficaDto(grafica, impressoes, contratos);
    }

    public void deletar(Long id){

        validadoresDeletarGrafica.stream()
                .forEach(v -> v.validar(id));

        graficaRepository.deleteById(id);
    }
}
