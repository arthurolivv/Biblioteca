package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.*;
import com.grafica.GraficaBD.domain.autor.validacoes.atualizarNomeEnderecoDoAutor.ValidadorAtualizarNomeEnderecoDoAutor;
import com.grafica.GraficaBD.domain.autor.validacoes.cadastrarAutor.ValidadorCadastrarAutor;
import com.grafica.GraficaBD.domain.autor.validacoes.deletarAutor.ValidadorDeletarAutor;
import com.grafica.GraficaBD.domain.autor.validacoes.detalharAutor.ValidadorDetalharAutor;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ValidadorCadastrarAutor validadorCadastrarAutor;

    @Autowired
    private ValidadorDetalharAutor validadorDetalharAutor;

    @Autowired
    private ValidadorAtualizarNomeEnderecoDoAutor validadorAtualizarNomeEnderecoDoAutor;

    @Autowired
    private ValidadorDeletarAutor validadorDeletarAutor;

    public CriarAutorDto cadastrar(CriarAutorDto criarAutorDto) {

        validadorCadastrarAutor.validar(criarAutorDto);
        var novoAutor = new Autor(criarAutorDto);
        autorRepository.save(novoAutor);
        return new CriarAutorDto(novoAutor);
    }
    public List<ListarAutorDto> listar() {

        List<ListarAutorDto> autores = autorRepository.findAll()
                .stream()
                .map(ListarAutorDto::new)
                .toList();

        return autores;
    }

    public DetalharAutorDto detalhar(String rg) {

        validadorDetalharAutor.validar(rg);
        var autor =  autorRepository.getReferenceById(rg);

        List<DetalharLivroSemAutorDto> livros = autor.getEscreve().stream()
                .map(Escreve::getLivro)
                .map(DetalharLivroSemAutorDto::new)
                .toList();
        return new DetalharAutorDto(autor.getNome(), autor.getEndereco(), livros);
    }

    public void atualizar(String rg, AtualizarNomeEnderecoDoAutorDto atualizarNomeEnderecoDoAutorDto) {

        validadorAtualizarNomeEnderecoDoAutor.validar(rg, atualizarNomeEnderecoDoAutorDto);
        var autor =  autorRepository.getReferenceById(rg);

        if (atualizarNomeEnderecoDoAutorDto.nome() != null && !atualizarNomeEnderecoDoAutorDto.nome().isBlank()) {
            autor.setNome(atualizarNomeEnderecoDoAutorDto.nome());
        }

        if (atualizarNomeEnderecoDoAutorDto.endereco() != null && !atualizarNomeEnderecoDoAutorDto.endereco().isBlank()) {
            autor.setEndereco(atualizarNomeEnderecoDoAutorDto.endereco());
        }

        autorRepository.save(autor);
    }

    public void deletar(String rg) {

        validadorDeletarAutor.validar(rg);
        autorRepository.deleteById(rg);

    }


}
