package com.grafica.GraficaBD.domain.imprime;

import com.grafica.GraficaBD.domain.grafica.Grafica;
import com.grafica.GraficaBD.domain.livro.Livro;
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
    @JoinColumn(name = "lisbn")
    private Livro livro;

    @ManyToOne
    @MapsId("grafica_id")
    @JoinColumn(name = "grafica_id")
    private Grafica grafica;

}
