package com.grafica.GraficaBD.domain.escreve.validacoes.criarEscreve;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorAutorExiste implements ValidadorCriarEscreve {

    @Autowired
    private AutorRepository autorRepository;

    public void validar(String rg, LivrosPorIsbnDto livrosPorIsbn) {

        if(!autorRepository.existsById(rg)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O autor '" + rg + "' não existe!");
        }

    }
}
