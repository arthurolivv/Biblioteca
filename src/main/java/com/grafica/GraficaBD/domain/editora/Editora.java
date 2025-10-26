package com.grafica.GraficaBD.domain.editora;

import com.grafica.GraficaBD.domain.livro.Livro;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Livro> livros;

    public Editora(CriarEditoraDto criarEditoraDto) {
        this.nome = criarEditoraDto.nome();
        this.endereco = criarEditoraDto.endereco();
    }
}
