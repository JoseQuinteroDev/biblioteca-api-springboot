package com.josequintero.biblioteca.repository;

import com.josequintero.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn); //derivación de consultas por nombre del método
    /*
    * Básicamente, Spring cuando arranca la app analiza el nombre de la función y comprende
    * que existsBy es básicamente dar true o false para el campo Isbn. Así que lo implementa automáticamente
    * diciendo: ¿hay algún dato en la columna Isbn que coindica con el argumento de la función?*/
}
