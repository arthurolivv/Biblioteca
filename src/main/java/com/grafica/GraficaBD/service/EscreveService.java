package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.DetalharAutorDto;
import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.escreve.validacoes.verificarExistencia.ValidadorRelacaoAutorELivrosExiste;
import com.grafica.GraficaBD.domain.escreve.validacoes.verificarExistencia.ValidadorRelacaoAutorELivrosNaoExiste;
import com.grafica.GraficaBD.domain.escreve.validacoes.verificarExistencia.ValidadorVerificarExistencia;
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
    private List<ValidadorVerificarExistencia> validadoresVerificarExistencia;
    @Autowired
    private EscreveRepository escreveRepository;


    public DetalharAutorDto vincularAutorELivros(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadoresVerificarExistencia.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoAutorELivrosNaoExiste))
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

    public DetalharAutorDto desvincularAutorELivros(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadoresVerificarExistencia.stream()
                .filter(v -> !(v instanceof ValidadorRelacaoAutorELivrosExiste))
                .forEach(v -> v.validar(rg, livrosPorIsbnDto));

        var autor = autorRepository.getReferenceById(rg);

        for(String isbn : livrosPorIsbnDto.isbns()){

            var escreve = escreveRepository.getReferenceById(new EscreveId(isbn, rg));

            autor.getEscreve().remove(escreve);
        }

        autorRepository.save(autor);
        return new DetalharAutorDto(autor);
    }

    public void vincularLivroEAutores() {}
}
