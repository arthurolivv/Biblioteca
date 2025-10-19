package com.grafica.GraficaBD.domain.grafica;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Particular")
@Table(name = "Particular")
@Getter
@Setter
@NoArgsConstructor
public class GraficaParticular extends Grafica{
}
