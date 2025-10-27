package com.grafica.GraficaBD.service;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.livro.*;
import com.grafica.GraficaBD.domain.livro.validacoes.atualizarTituloDataPublicacaoEditoraDoLivro.ValidadorAtualizarTituloDataPublicacaoEditoraDoLivro;
import com.grafica.GraficaBD.domain.livro.validacoes.cadastrarLivro.ValidadorCadastrarLivro;
import com.grafica.GraficaBD.domain.livro.validacoes.deletarLivro.ValidadorDeletarLivro;
import com.grafica.GraficaBD.domain.livro.validacoes.detalharLivro.ValidadorDetalharLivro;
import com.grafica.GraficaBD.repository.EditoraRepository;
import com.grafica.GraficaBD.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private List<ValidadorCadastrarLivro> validadorCadastrarLivros;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private List<ValidadorDetalharLivro> validadorDetalharLivros;

    @Autowired
    private List<ValidadorDeletarLivro> validadorDeletarLivros;

    @Autowired
    private List<ValidadorAtualizarTituloDataPublicacaoEditoraDoLivro> atualizarTituloDataPublicacaoEditoraDoLivro;

    public CadastrarLivroDto cadastrar(CadastrarLivroDto cadastrarLivroDto){

        validadorCadastrarLivros.stream().forEach(v -> v.validar(cadastrarLivroDto));
        var novoLivro = new Livro(cadastrarLivroDto);
        livroRepository.save(novoLivro);
        return new CadastrarLivroDto(novoLivro);
    }

    public List<ListarLivroDto> listar(){

        List<ListarLivroDto> livros = livroRepository.findAll()
                .stream()
                .map(ListarLivroDto::new)
                .toList();

        return livros;
    }

    public DetalharLivroDto detalhar(String isbn){

        validadorDetalharLivros.stream()
                .forEach(v -> v.validar(isbn));

        var livro =  livroRepository.getReferenceById(isbn);

        List<DetalharNomeRgAutorDto> autores = livro.getEscreve()
                .stream()
                .map(Escreve::getAutor)
                .map(DetalharNomeRgAutorDto::new)
                .toList();

        return new DetalharLivroDto(livro, autores);
    }

    public void atualizar(String isbn, AtualizarTituloDataPublicacaoEditoraDoLivroDto atualizarTituloDataPublicacaoEditoraDoLivroDto){

        atualizarTituloDataPublicacaoEditoraDoLivro.stream().forEach(v->v.validar(isbn,atualizarTituloDataPublicacaoEditoraDoLivroDto));

        var livro = livroRepository.getReferenceById(isbn);

        var editora = editoraRepository.getReferenceById(atualizarTituloDataPublicacaoEditoraDoLivroDto.editora_id());

        if(atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo() != null && !atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo().isBlank()){
            livro.setTitulo(atualizarTituloDataPublicacaoEditoraDoLivroDto.titulo());
        }

        if(atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao() != null && !atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao().toString().isBlank()){
            livro.setData_de_publicacao(atualizarTituloDataPublicacaoEditoraDoLivroDto.data_de_publicacao());
        }

        if(atualizarTituloDataPublicacaoEditoraDoLivroDto.editora_id() != null){
            livro.setEditora(editora);
        }
        else{
            livro.setEditora(null);
        }

        livroRepository.save(livro);
    }

    public void deletar(String isbn){

        validadorDeletarLivros.stream()
                .forEach(v -> v.validar(isbn));

        var livro = livroRepository.getReferenceById(isbn);
        livroRepository.delete(livro);
    }
}
