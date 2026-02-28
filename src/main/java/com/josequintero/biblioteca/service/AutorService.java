package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.AutorCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;

import java.util.List;

public interface AutorService {
    AutorResponse crear(AutorCreateRequest request);
    List<AutorResponse> listar();
    AutorResponse obtenerPorId(Long id);
    void eliminar(Long id);
}