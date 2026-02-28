package com.josequintero.biblioteca.dto.response;

import com.josequintero.biblioteca.model.Autor;

public class AutorResponse {

    private Long id;
    private String nombre;
    private String pais;

    public AutorResponse(Long id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public AutorResponse() {

    }


    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
}