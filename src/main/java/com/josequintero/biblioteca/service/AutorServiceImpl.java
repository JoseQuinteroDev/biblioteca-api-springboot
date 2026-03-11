package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.AutorCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;
import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    @Transactional
    public AutorResponse crear(AutorCreateRequest request) {
        Autor autor = new Autor(request.getNombre(), request.getPais());
        Autor guardado = autorRepository.save(autor);

        return new AutorResponse(guardado.getId(), guardado.getNombre(), guardado.getPais());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutorResponse> listar() {
        return autorRepository.findAll()
                .stream()
                .map(a -> new AutorResponse(a.getId(), a.getNombre(), a.getPais()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AutorResponse obtenerPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autor con id " + id + " no encontrado"
                ));

        return new AutorResponse(autor.getId(), autor.getNombre(), autor.getPais());
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado con id: " + id);
        }
        autorRepository.deleteById(id);
    }
}
