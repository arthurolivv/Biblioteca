package com.grafica.GraficaBD.domain.grafica;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Particular")
@Table(name = "Particular")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "grafica_id")

public class GraficaParticular extends Grafica{

    public GraficaParticular(CadastrarGraficaDto cadastrarGraficaDto) {
        super(cadastrarGraficaDto);
    }
}
