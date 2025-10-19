package com.biblioteca.BibliotecaBD.repository;

import com.biblioteca.BibliotecaBD.domain.editora.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
