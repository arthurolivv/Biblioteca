package com.grafica.GraficaBD.domain.escreve;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class EscreveId implements Serializable {

    private String isbn;

    private String rg;
}
