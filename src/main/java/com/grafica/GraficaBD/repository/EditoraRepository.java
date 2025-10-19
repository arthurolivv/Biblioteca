package com.grafica.GraficaBD.repository;

import com.grafica.GraficaBD.domain.editora.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
