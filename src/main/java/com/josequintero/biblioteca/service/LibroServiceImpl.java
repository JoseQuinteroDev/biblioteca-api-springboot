package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.LibroCreateRequest;
import com.josequintero.biblioteca.dto.response.LibroResponse;
import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.model.Libro;
import com.josequintero.biblioteca.repository.AutorRepository;
import com.josequintero.biblioteca.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    //Inyecciones de dependencia (Spring directamente inyecta, no hace falta crearlos)
    public LibroServiceImpl(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    @Override
    @Transactional
    public LibroResponse crear(LibroCreateRequest request) {

        // 1) Regla de negocio: ISBN único -> 409 CONFLICT
        if (libroRepository.existsByIsbn(request.getIsbn())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un libro con ISBN: " + request.getIsbn()
            );
        }

        // 2) El autor debe existir -> 404 NOT_FOUND
        Autor autor = autorRepository.findById(request.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autor no encontrado con id: " + request.getAutorId()
                ));

        // 3) Crear entity y guardar
        Libro libro = new Libro(request.getTitulo(), request.getIsbn(), autor);
        Libro guardado = libroRepository.save(libro);

        // 4) Mapear a response
        return new LibroResponse(
                guardado.getId(),
                guardado.getTitulo(),
                guardado.getIsbn(),
                guardado.getAutor().getId(),
                guardado.getAutor().getNombre()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<LibroResponse> listar() {
        return libroRepository.findAll()
                .stream()
                .map(l -> new LibroResponse(
                        l.getId(),
                        l.getTitulo(),
                        l.getIsbn(),
                        l.getAutor().getId(),
                        l.getAutor().getNombre()
                ))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LibroResponse obtenerPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado con id: " + id));

        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getAutor().getId(),
                libro.getAutor().getNombre()
        );
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }
}