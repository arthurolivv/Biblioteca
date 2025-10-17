package com.biblioteca.BibliotecaBD.domain.editora;

import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Editora")
@Entity(name = "Editora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros;
}
