package com.josequintero.biblioteca.controller;

import com.josequintero.biblioteca.dto.request.LibroCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;
import com.josequintero.biblioteca.dto.response.LibroResponse;
import com.josequintero.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroResponse> crear(@Valid @RequestBody LibroCreateRequest request)
    {
        LibroResponse response = libroService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);   // si no existe, aquí salta excepción 404 y no sigue
        return ResponseEntity.noContent().build(); // solo llega aquí si se borró bien
    }

    @GetMapping("/{id}")
    public LibroResponse obtenerPorIdC(@PathVariable Long id)
    {
        return libroService.obtenerPorId(id);

    }

    @GetMapping
    public List<LibroResponse> listar()
    {
        return libroService.listar();
    }

}
