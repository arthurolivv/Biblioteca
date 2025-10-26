package com.grafica.GraficaBD.domain.escreve.validacoes.verificarExistencia;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorRelacaoAutorELivrosExiste implements ValidadorVerificarExistencia {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    ValidadorAutorExiste validadorAutorExiste;

    @Autowired
    ValidadorLivroExiste validadorLivroExiste;

    public void validar(String rg, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadorAutorExiste.validar(rg, livrosPorIsbnDto);

        var autor = autorRepository.getReferenceById(rg);

        validadorLivroExiste.validar(rg, livrosPorIsbnDto);

        for(String isbn : livrosPorIsbnDto.isbns()){

            boolean jaExiste = autor.getEscreve()
                    .stream()
                    .anyMatch(e -> e.getLivro().getIsbn().equals(isbn));

            if(jaExiste) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A relação entre o autor '" + rg + "' e o livro '" + isbn + "' já existe!");
            }
        }
    }
}
