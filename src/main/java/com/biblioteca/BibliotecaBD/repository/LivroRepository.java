package com.biblioteca.BibliotecaBD.repository;

import com.biblioteca.BibliotecaBD.domain.livro.Livro;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, String> {
}
