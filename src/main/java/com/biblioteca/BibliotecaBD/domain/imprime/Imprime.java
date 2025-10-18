package com.biblioteca.BibliotecaBD.domain.imprime;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import com.biblioteca.BibliotecaBD.domain.grafica.Grafica;
import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "imprime")
@Entity(name = "Imprime")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Imprime {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ImprimeId imprimeId;

    private Long nto_copias;

    private LocalDate data_entrega;

    @ManyToOne
    @MapsId("lisbn")
    @JoinColumn(name = "lsbn")
    private Livro livro;

    @ManyToOne
    @MapsId("grafica_id")
    @JoinColumn(name = "grafica_id")
    private Grafica grafica;

}
