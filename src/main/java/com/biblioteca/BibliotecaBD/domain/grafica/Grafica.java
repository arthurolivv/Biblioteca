package com.biblioteca.BibliotecaBD.domain.grafica;

import com.biblioteca.BibliotecaBD.domain.imprime.Imprime;
import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Grafica")
@Entity(name = "Grafica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Grafica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    protected Long id;

    @Column(nullable = false)
    protected String nome;

    @OneToMany(mappedBy = "grafica")
    private List<Imprime> imprime;
}
