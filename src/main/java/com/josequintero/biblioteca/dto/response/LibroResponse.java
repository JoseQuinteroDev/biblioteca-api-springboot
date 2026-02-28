package com.josequintero.biblioteca.dto.response;

import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.model.Libro;

public class LibroResponse {

    private Long id;
    private String titulo;
    private String isbn;

    private Long autorId;
    private String autorNombre; // ejemplo: "Miguel de Cervantes"

    public LibroResponse(Long id, String titulo, String isbn, Long autorId, String autorNombre) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autorId = autorId;
        this.autorNombre = autorNombre;
    }


    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }

    public Long getAutorId() { return autorId; }
    public String getAutorNombre() { return autorNombre; }
}