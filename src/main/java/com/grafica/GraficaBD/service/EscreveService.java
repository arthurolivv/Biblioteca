package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.DetalharAutorDto;
import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import com.grafica.GraficaBD.domain.escreve.validacoes.criarEscreve.ValidadorCriarEscreve;
import com.grafica.GraficaBD.repository.AutorRepository;
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
    private List<ValidadorCriarEscreve> validadoresCriarVinculacao;


    public DetalharAutorDto vincularAutorELivros(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadoresCriarVinculacao.forEach(v -> v.validar(rg, livrosPorIsbnDto));

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

    public void vincularLivroEAutores() {}
}
