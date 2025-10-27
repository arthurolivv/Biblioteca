package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.grafica.Grafica;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraficaRepository extends JpaRepository<Grafica, Long> {
    boolean existsByNome(@NotBlank String nome);
}
