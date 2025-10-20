package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.escreve.Escreve;
import com.grafica.GraficaBD.domain.escreve.EscreveId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscreveRepository extends JpaRepository<Escreve, EscreveId> {
}
