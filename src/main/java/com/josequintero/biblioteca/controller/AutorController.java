package com.josequintero.biblioteca.controller;

import com.josequintero.biblioteca.dto.request.AutorCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;
import com.josequintero.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<AutorResponse> crear(@Valid @RequestBody AutorCreateRequest request) {
        AutorResponse creado = autorService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public List<AutorResponse> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorResponse obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        autorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
