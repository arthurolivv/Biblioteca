package com.grafica.GraficaBD.domain.livro;

import com.grafica.GraficaBD.domain.autor.Autor;

public record DetalharNomeRgAutorDto(

        String nome,
        String rg
) {

    public DetalharNomeRgAutorDto (Autor autor){
            this(   autor.getNome(),
                    autor.getRg());
        }
}
