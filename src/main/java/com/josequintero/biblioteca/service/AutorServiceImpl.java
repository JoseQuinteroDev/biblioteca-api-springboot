package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.AutorCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;
import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.repository.AutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

        return new AutorResponse(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getPais()
        );
    }

    @Override
    @Transactional (readOnly = true)
    public List<AutorResponse> listar() {
         return autorRepository.findAll()
             .stream()
             .map(a -> new AutorResponse(a.getId(), a.getNombre(), a.getPais()))
             .toList();
//        List<Autor> obtener  = autorRepository.findAll();
//        List<AutorResponse> respuesta = List.of();
//        for (Autor a : obtener)
//        {
//            respuesta.add(new AutorResponse(a.getId(), a.getNombre(), a.getPais()));
//        }
//        return respuesta;

    }



    @Override
    @Transactional (readOnly = true)
    public AutorResponse obtenerPorId(Long id) {
        /*
        * Spring Data JPA lo que hace con findById es lanzar un optional y si existe,
        *  pues obtenemos el autor y devolvemos. Si está vacío, en este casp lanzamos excepción*/
       Autor autor = autorRepository.findById(id).orElseThrow(
               () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "autor con id " + id + " no encontrado" ) {
       });

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