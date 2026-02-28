package com.josequintero.biblioteca.repository;

import com.josequintero.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}