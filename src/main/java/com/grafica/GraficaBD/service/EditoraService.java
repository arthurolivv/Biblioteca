package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.autor.CriarAutorDto;
import com.grafica.GraficaBD.domain.autor.LivrosPorIsbnDto;
import com.grafica.GraficaBD.domain.autor.validacoes.detalharAutor.ValidadorDetalharAutor;
import com.grafica.GraficaBD.domain.editora.*;
import com.grafica.GraficaBD.domain.editora.validacoes.ValidadorDesvincularLivro.ValidadorDesvincularLivro;
import com.grafica.GraficaBD.domain.editora.validacoes.atualizarNomeEnderecoEditora.ValidadorAtualizarNomeEnderecoEditora;
import com.grafica.GraficaBD.domain.editora.validacoes.cadastrarEditora.ValidadorCadastrarEditora;
import com.grafica.GraficaBD.domain.editora.validacoes.deletarEditora.ValidadorDeletarEditora;
import com.grafica.GraficaBD.domain.editora.validacoes.detalharEditora.ValidadorDetalharEditora;
import com.grafica.GraficaBD.domain.editora.validacoes.vincularLivro.ValidadorVincularLivro;
import com.grafica.GraficaBD.domain.livro.Livro;
import com.grafica.GraficaBD.repository.EditoraRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private ValidadorCadastrarEditora  validadorCadastrarEditora;

    @Autowired
    private List<ValidadorVincularLivro> validadoresVincularLivros;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private List<ValidadorDetalharEditora> validadoresDetalharEditora;

    @Autowired
    private List<ValidadorAtualizarNomeEnderecoEditora> validadoresAtualizarNomeEnderecoEditora;

    @Autowired
    private List<ValidadorDesvincularLivro> validadorDesvincularLivros;

    @Autowired List<ValidadorDeletarEditora> validadorDeletarEditora;

    public DetalharEditoraDto cadastrar(CriarEditoraDto criarEditoraDto){

        validadorCadastrarEditora.validar(criarEditoraDto);
        var novaEditora = new Editora(criarEditoraDto);
        editoraRepository.save(novaEditora);
        return new DetalharEditoraDto(novaEditora);

    }

    public DetalharEditoraDto vincularLivro(Long id, LivrosPorIsbnDto livrosPorIsbnDto){

        validadoresVincularLivros.stream()
                .forEach(v -> v.validar(id, livrosPorIsbnDto));

        var editora = editoraRepository.getReferenceById(id);
        var livros = livroRepository.findAllById(livrosPorIsbnDto.isbns());

        for(Livro novoLivro : livros){

            novoLivro.setEditora(editora);
            editora.getLivros().add(novoLivro);
        }

        editoraRepository.save(editora);
        return new DetalharEditoraDto(editora);
    }

    public List<ListarEditoraDto> listar(){

        List<ListarEditoraDto> editoras = editoraRepository.findAll()
                .stream()
                .map(ListarEditoraDto::new)
                .toList();

        return editoras;
    }

    public DetalharEditoraDto detalhar(Long id){

        validadoresDetalharEditora.stream()
                .forEach(v -> v.validar(id));

        var editora = editoraRepository.getReferenceById(id);

        List<String> livrosTitulos = editora.getLivros()
                .stream()
                .map(Livro::getTitulo)
                .toList();

        return new DetalharEditoraDto(editora.getId(), editora.getNome(), editora.getEndereco(), livrosTitulos);

    }

    public void atualizar(Long id, AtualizarNomeEnderecoDaEditoraDto atualizarNomeEnderecoDaEditoraDto){

        validadoresAtualizarNomeEnderecoEditora.stream()
                .forEach(v -> v.validar(id, atualizarNomeEnderecoDaEditoraDto));

        var editora = editoraRepository.getReferenceById(id);

        if (atualizarNomeEnderecoDaEditoraDto.nome() != null && !atualizarNomeEnderecoDaEditoraDto.nome().isBlank()) {
            editora.setNome(atualizarNomeEnderecoDaEditoraDto.nome());
        }
        if (atualizarNomeEnderecoDaEditoraDto.endereco() != null && !atualizarNomeEnderecoDaEditoraDto.endereco().isBlank()) {
            editora.setEndereco(atualizarNomeEnderecoDaEditoraDto.endereco());
        }

        editoraRepository.save(editora);

    }

    public void desvicularLivro(Long id, LivrosPorIsbnDto livrosPorIsbnDto){

        validadorDesvincularLivros.stream()
                .forEach(v -> v.validar(id, livrosPorIsbnDto));

        var editora = editoraRepository.getReferenceById(id);
        var livros =  livroRepository.findAllById(livrosPorIsbnDto.isbns());

        for(Livro livro : livros){

            livro.setEditora(null);
            editora.getLivros().remove(livro);
        }

        editoraRepository.save(editora);
    }

    public void deletar(Long id){

        validadorDeletarEditora.stream()
                .forEach(v -> v.validar(id));
        editoraRepository.deleteById(id);
    }
}
