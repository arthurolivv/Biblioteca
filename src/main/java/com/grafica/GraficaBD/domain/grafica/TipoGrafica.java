package com.grafica.GraficaBD.domain.grafica;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
public enum TipoGrafica {

    PARTICULAR("P","Particular"),
    CONTRATADA("C", "Contratada");

    private final String sigla;
    private final String nomeCompleto;

    TipoGrafica(String sigla, String nomeCompleto) {
        this.sigla = sigla;
        this.nomeCompleto = nomeCompleto;
    }

    @JsonCreator
    public static TipoGrafica fromString(String valor) {
        if (valor == null) return null;

        //converte para os valores maiusculos e remove espaços em branco,
        //desse modo, ele se encaixa automaticamente em qualquer definicao
        String normalizado = valor.trim().toUpperCase();

        for (TipoGrafica tipo_grafica : values()) {
            if (tipo_grafica.sigla.equals(normalizado) ||
                    tipo_grafica.nomeCompleto.toUpperCase().equals(normalizado) ||
                    tipo_grafica.name().equals(normalizado)) {
                 return tipo_grafica;
            }
        }

        throw new IllegalArgumentException("Estado inválido: " + valor);
    }

}
