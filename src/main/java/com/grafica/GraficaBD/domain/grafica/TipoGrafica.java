package com.grafica.GraficaBD.domain.grafica;

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

    public static TipoGrafica fromString(String valor) {
        if (valor == null) return null;

        //converte para os valores maiusculos e remove espaços em branco,
        //desse modo, ele se encaixa automaticamente em qualquer definicao
        String normalizado = valor.trim().toUpperCase();

        for (TipoGrafica tipoGrafica : values()) {
            if (tipoGrafica.sigla.equals(normalizado) || tipoGrafica.nomeCompleto.equals(normalizado)) {
                return tipoGrafica;
            }
        }

        throw new IllegalArgumentException("Estado inválido: " + valor);
    }

}
