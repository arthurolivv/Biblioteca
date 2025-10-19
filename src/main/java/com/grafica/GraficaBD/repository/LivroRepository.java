package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, String> {
}
