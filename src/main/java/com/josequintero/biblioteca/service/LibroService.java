package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.LibroCreateRequest;
import com.josequintero.biblioteca.dto.response.LibroResponse;

import java.util.List;

public interface LibroService {
    LibroResponse crear(LibroCreateRequest request);
    List<LibroResponse> listar();
    LibroResponse obtenerPorId(Long id);
    void eliminar(Long id);
}