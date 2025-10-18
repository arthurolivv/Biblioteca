package com.biblioteca.BibliotecaBD.domain.autor;

import com.biblioteca.BibliotecaBD.domain.escreve.Escreve;
import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "Autor")
@Entity(name = "Autor")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @Column(name = "RG")
    private String rg;

    private String nome;

    private String endereco;

    @OneToMany(mappedBy = "autor")
    private List<Escreve> escreve;
}
