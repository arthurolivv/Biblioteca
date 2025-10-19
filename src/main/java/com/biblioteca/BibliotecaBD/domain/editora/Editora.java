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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private List<Livro> livros;

    public Editora(EditoraDto editoraDto) {
        this.nome = editoraDto.nome();
        this.endereco = editoraDto.endereco();
    }
}
