package com.grafica.GraficaBD.domain.editora.validacoes.ValidadorDesvincularLivro;

import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro.ValidadorEditoraNaoExisteVincularLivro;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ValidadorVinculacaoEditoraELivroNaoExisteDesvincularLivro implements ValidadorDesvincularLivro{

    @Autowired
    private ValidadorEditoraNaoExisteVincularLivro validadorEditoraNaoExisteVincularLivro;

    @Autowired
    private ValidadorIsbnLivroNaoExisteDesvincularLivro validadorIsbnLivroNaoExisteDesvincularLivro;

    @Autowired
    private LivroRepository livroRepository;
    @Override
    public void validar(Long id, LivrosPorIsbnDto livrosPorIsbnDto) {

        validadorEditoraNaoExisteVincularLivro.validar(id, livrosPorIsbnDto);
        validadorIsbnLivroNaoExisteDesvincularLivro.validar(id, livrosPorIsbnDto);

        List<Livro> livros = livroRepository.findAllById(livrosPorIsbnDto.isbns());

        for(Livro livro : livros){

            if(!livro.getEditora().getId().equals(id)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A editora '" + id +
                        "' não está vinculada ao livro '" + livro.getIsbn() + "'!");
            }
        }

    }
}
