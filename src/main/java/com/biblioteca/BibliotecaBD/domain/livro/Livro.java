package com.biblioteca.BibliotecaBD.domain.livro;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import com.biblioteca.BibliotecaBD.domain.editora.EditoraDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "Livros")
@Entity(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "isbn")
public class Livro {

    @Id
    private String isbn;
    private String titulo;
    private LocalDate data_de_publicacao;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY - dados serao carregados sob demanda
    @JoinColumn(name="editora_id")
    private Editora editora;
}
