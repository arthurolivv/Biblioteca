package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.grafica.Grafica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraficaRepository extends JpaRepository<Grafica, Long> {
}
