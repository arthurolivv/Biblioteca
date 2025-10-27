package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.DetalharAutorDto;
import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.escreve.validacoes.vinculacaoAutorELivros.ValidadorRelacaoAutorELivrosNaoExisteVinculacaoAutorELivros;
import com.grafica.GraficaBD.domain.escreve.validacoes.vinculacaoAutorELivros.ValidadorRelacaoAutorELivrosExisteVinculacaoAutorELivros;
import com.grafica.GraficaBD.domain.escreve.validacoes.vinculacaoAutorELivros.ValidadorVinculacaoAutorELivros;
import com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores.ValidadorRelacaoLivroEAutorExisteVinculacaoLivroEAutores;
import com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores.ValidadorRelacaoLivroEAutorNaoExisteVinculacaoLivroEAutores;
import com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores.ValidadorVinculacaoLivroEAutores;
import com.grafica.GraficaBD.domain.livro.AutoresPorRgDto;
import com.grafica.GraficaBD.domain.livro.DetalharLivroDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.EscreveRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscreveService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private List<ValidadorVinculacaoAutorELivros> validadoresVerificarExistenciaAutorELivros;

    @Autowired
    private List<ValidadorVinculacaoLivroEAutores> validadoresVerificarExistenciaLivroEAutores;

    @Autowired
    private EscreveRepository escreveRepository;


    public DetalharAutorDto vincularAutorELivros(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadoresVerificarExistenciaAutorELivros.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoAutorELivrosExisteVinculacaoAutorELivros))
                .forEach(v -> v.validar(rg, livrosPorIsbnDto));

        var autor = autorRepository.getReferenceById(rg);

        for(String isbn : livrosPorIsbnDto.isbns()){

            var livro = livroRepository.getReferenceById(isbn);

            var escreve =  new Escreve(new EscreveId(isbn, rg),
                    livro,
                    autor);

            autor.getEscreve().add(escreve);
        }

        autorRepository.save(autor);
        return new DetalharAutorDto(autor);
    }

    public void desvincularAutorELivros(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadoresVerificarExistenciaAutorELivros.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoAutorELivrosNaoExisteVinculacaoAutorELivros))
                .forEach(v -> v.validar(rg, livrosPorIsbnDto));

        var autor = autorRepository.getReferenceById(rg);

        for(String isbn : livrosPorIsbnDto.isbns()){

            var escreve = escreveRepository.getReferenceById(new EscreveId(isbn, rg));

            autor.getEscreve().remove(escreve);
        }

        autorRepository.save(autor);
    }

    public DetalharLivroDto vincularLivroEAutores(String isbn, AutoresPorRgDto autoresPorRgDto){

        validadoresVerificarExistenciaLivroEAutores.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoLivroEAutorExisteVinculacaoLivroEAutores))
                .forEach(v -> v.validar(isbn, autoresPorRgDto));

        var livro = livroRepository.getReferenceById(isbn);

        for(String autor_rg : autoresPorRgDto.autores_rg()){

            var autor = autorRepository.getReferenceById(autor_rg);

            var escreve = new Escreve(new EscreveId(isbn, autor_rg),
                    livro,
                    autor);

            livro.getEscreve().add(escreve);
        }
        livroRepository.save(livro);
        return new DetalharLivroDto(livro);

    }

    public void desvincularLivroEAutores(String isbn, AutoresPorRgDto autoresPorRgDto){

        validadoresVerificarExistenciaLivroEAutores.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoLivroEAutorNaoExisteVinculacaoLivroEAutores))
                .forEach(v -> v.validar(isbn, autoresPorRgDto));

        var livro = livroRepository.getReferenceById(isbn);

        for(String autor_rg : autoresPorRgDto.autores_rg()){

            var escreve = escreveRepository.getReferenceById(new EscreveId(isbn, autor_rg));

            livro.getEscreve().remove(escreve);
        }
        livroRepository.save(livro);
    }
}
