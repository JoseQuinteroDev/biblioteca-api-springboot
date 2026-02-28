package com.josequintero.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LibroCreateRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede superar 200 caracteres")
    private String titulo;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 20, message = "El ISBN no puede superar 20 caracteres")
    private String isbn;

    @NotNull(message = "El autorId es obligatorio")
    private Long autorId;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
}