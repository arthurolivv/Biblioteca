package com.grafica.GraficaBD.domain.escreve.validacoes.vinculacaoAutorELivros;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorRelacaoAutorELivrosExisteVinculacaoAutorELivros implements ValidadorVinculacaoAutorELivros {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    ValidadorAutorExisteVinculacaoAutorELivros validadorAutorExisteVinculacaoAutorELivros;

    @Autowired
    ValidadorLivroExisteVinculacaoAutorELivros validadorLivroExisteVinculacaoAutorELivros;

    public void validar(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadorAutorExisteVinculacaoAutorELivros.validar(rg, livrosPorIsbnDto);

        var autor = autorRepository.getReferenceById(rg);

        validadorLivroExisteVinculacaoAutorELivros.validar(rg, livrosPorIsbnDto);

        for(String isbn : livrosPorIsbnDto.isbns()){

            boolean jaExiste = autor.getEscreve()
                    .stream()
                    .anyMatch(e -> e.getLivro().getIsbn().equals(isbn));

            if(!jaExiste) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A relação entre o autor '" + rg + "' e o livro '" + isbn + "' não existe!");
            }
        }
    }
}
