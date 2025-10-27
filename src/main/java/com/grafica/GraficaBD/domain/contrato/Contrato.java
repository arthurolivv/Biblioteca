package com.grafica.GraficaBD.domain.contrato;

import com.grafica.GraficaBD.domain.grafica.AdicionarContratoNaGraficaDto;
import com.grafica.GraficaBD.domain.grafica.GraficaContratada;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Contrato")
@Entity(name = "Contrato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String nome_responsavel;

    @ManyToOne
    @JoinColumn(name = "grafica_cont_id")
    private GraficaContratada grafica_cont;

    public Contrato(AdicionarContratoNaGraficaDto adicionarContrato, GraficaContratada grafica_cont) {
        this.valor = adicionarContrato.valor();
        this.nome_responsavel = adicionarContrato.nome_responsavel();
        this.grafica_cont = grafica_cont;
    }
}
