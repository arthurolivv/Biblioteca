package com.biblioteca.BibliotecaBD.domain.grafica;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Contrato")
@Entity(name = "Contrato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contrato {

    @Id
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String nome_responsavel;

    @ManyToOne
    @JoinColumn(name = "grafica_cont_id")
    private GraficaContratada grafica_cont;

}
