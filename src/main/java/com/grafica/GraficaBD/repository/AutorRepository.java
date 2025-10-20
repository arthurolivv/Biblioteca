package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, String> {
}
