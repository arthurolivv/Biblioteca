package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.CriarAutorDto;
import com.grafica.GraficaBD.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void Teste(CriarAutorDto criarAutorDto) {


    }


}
