package com.grafica.GraficaBD.domain.autor;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Autor")
@Entity(name = "Autor")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @Column(name = "RG")
    private String rg;

    private String nome;

    private String endereco;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Escreve> escreve = new ArrayList<>();

    public Autor(CriarAutorDto criarAutorDto) {
        this.rg = criarAutorDto.RG();
        this.nome = criarAutorDto.nome();
        this.endereco = criarAutorDto.endereco();
    }
}
