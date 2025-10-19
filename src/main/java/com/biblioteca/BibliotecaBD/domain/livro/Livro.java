package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.autor.Autor;
import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import com.biblioteca.BibliotecaBD.domain.editora.EditoraDto;
import com.biblioteca.BibliotecaBD.domain.escreve.Escreve;
import com.biblioteca.BibliotecaBD.domain.imprime.Imprime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "Livros")
@Entity(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "isbn")
public class Livro {

    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate data_de_publicacao;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY - dados serao carregados sob demanda
    @JoinColumn(name="editora_id")
    @JsonIgnore
    private Editora editora;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Escreve> escreve;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Imprime> imprime;

    public Livro(LivroIsbnDto livroIsbnDto, Editora editora){
        this.editora = editora;
        this.isbn = livroIsbnDto.ISBN();
    }

    public Livro(LivroDto livroDto) {
        this.isbn = livroDto.ISBN();
        this.titulo = livroDto.titulo();
        this.data_de_publicacao = livroDto.data_de_publicacao();
    }
}
