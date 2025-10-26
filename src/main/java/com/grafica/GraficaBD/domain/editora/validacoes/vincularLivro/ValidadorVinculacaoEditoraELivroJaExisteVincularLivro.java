package com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ValidadorVinculacaoEditoraELivroJaExisteVincularLivro implements ValidadorVincularLivro {

    @Autowired
    private ValidadorEditoraNaoExisteVincularLivro validadorEditoraNaoExisteVincularLivro;
    @Autowired
    private ValidadorIsbnLivroNaoExisteVincularLivro validadorIsbnLivroNaoExisteVincularLivro;
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadorEditoraNaoExisteVincularLivro.validar(id, livrosPorIsbnDto);
        validadorIsbnLivroNaoExisteVincularLivro.validar(id, livrosPorIsbnDto);

        List<Livro> livros = livroRepository.findAllById(livrosPorIsbnDto.isbns());
        //usar isso pra evitar querys desnecessárias

        for(Livro livro : livros){
            if(livro.getEditora().getId().equals(id)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A editora '" + id +
                        "' já está vinculada ao livro '" + livro.getIsbn() + "'!");
            }
        }
    }
}
