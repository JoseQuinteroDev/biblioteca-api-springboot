package com.josequintero.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutorCreateRequest {

    @NotBlank(message = "El nombre es obligatorio") //Comprueba que no sea nulo, que no sea cadena vacía y encima que no sea solo espacios
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @Size(max = 80, message = "La nacionalidad no puede superar 80 caracteres")
    private String pais;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}