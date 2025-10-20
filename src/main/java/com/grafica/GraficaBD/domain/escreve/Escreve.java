package com.grafica.GraficaBD.domain.escreve;

import com.grafica.GraficaBD.domain.autor.Autor;
import com.grafica.GraficaBD.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Escreve")
@Table(name = "Escreve")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Escreve {

    @EqualsAndHashCode.Include
    @EmbeddedId
    private EscreveId escreveId;

    @ManyToOne
    @MapsId("isbn")
    @JoinColumn(name = "ISBN")
    private Livro livro;

    @ManyToOne
    @MapsId("rg")
    @JoinColumn(name = "RG")
    private Autor autor;
}
