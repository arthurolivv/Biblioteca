package com.grafica.GraficaBD.domain.grafica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "Contratada")
@Entity(name = "Contratada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "grafica_id")
public class GraficaContratada extends Grafica{

    @Column(nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "grafica_cont")
    private List<Contrato> contrato;
}
