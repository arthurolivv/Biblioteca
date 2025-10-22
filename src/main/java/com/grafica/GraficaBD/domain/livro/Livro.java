package com.grafica.GraficaBD.domain.livro;

import com.grafica.GraficaBD.domain.editora.Editora;
import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.imprime.Imprime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Livros")
@Entity(name = "Livros")
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

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Escreve> escreve;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Imprime> imprime;

    public Livro(LivroIsbnDto livroIsbnDto, Editora editora){
        this.editora = editora;
        this.isbn = livroIsbnDto.ISBN();
    }

    public Livro(CadastrarLivroDto livroDto) {
        this.isbn = livroDto.ISBN();
        this.titulo = livroDto.titulo();
        this.data_de_publicacao = livroDto.data_de_publicacao();
        this.escreve = new ArrayList<>();
        this.imprime = new ArrayList<>();
    }

}
