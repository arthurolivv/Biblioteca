package com.grafica.GraficaBD.domain.escreve.vinculacaoLivroEAutores;

import com.grafica.GraficaBD.domain.livro.AutoresPorRgDto;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorRelacaoLivroEAutorNaoExisteVinculacaoLivroEAutores implements ValidadorVinculacaoLivroEAutores {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ValidadorLivroExisteVinculacaoLivroEAutores validadorLivroExisteVinculacaoLivroEAutores;

    @Autowired
    private ValidadorAutorExisteVinculacaoLivroEAutores validadorAutorExisteVinculacaoLivroEAutores;

    @Override
    public void validar(String isbn, AutoresPorRgDto autoresPorRgDto) {

        validadorLivroExisteVinculacaoLivroEAutores.validar(isbn, autoresPorRgDto);

        var livro = livroRepository.getReferenceById(isbn);

        validadorAutorExisteVinculacaoLivroEAutores.validar(isbn, autoresPorRgDto);

        for(String autor_rg : autoresPorRgDto.autores_rg()){

            boolean jaExiste = livro.getEscreve()
                    .stream()
                    .anyMatch(autor -> autor.equals(autor_rg));

            if(jaExiste){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A relação entre o livro '" + isbn + "' e o autor '" + autor_rg + "' já existe!");
            }
        }
    }
}
