package com.grafica.GraficaBD.domain.imprime;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ImprimeId {

    private String lisbn;

    private Long grafica_id;
}
