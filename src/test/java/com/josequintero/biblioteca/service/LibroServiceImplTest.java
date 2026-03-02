package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.LibroCreateRequest;
import com.josequintero.biblioteca.dto.response.LibroResponse;
import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.model.Libro;
import com.josequintero.biblioteca.repository.AutorRepository;
import com.josequintero.biblioteca.repository.LibroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    @DisplayName("listar() -> devuelve libros mapeados a LibroResponse")
    void listar_deberiaDevolverListaDeLibrosResponse() {
        // Arrange
        Autor autor = mock(Autor.class);
        when(autor.getId()).thenReturn(10L);
        when(autor.getNombre()).thenReturn("Gabriel García Márquez");

        Libro libro = mock(Libro.class);
        when(libro.getId()).thenReturn(100L);
        when(libro.getTitulo()).thenReturn("Cien años de soledad");
        when(libro.getIsbn()).thenReturn("9780307474728");
        when(libro.getAutor()).thenReturn(autor);

        when(libroRepository.findAll()).thenReturn(List.of(libro));

        // Act
        List<LibroResponse> resultado = libroService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        LibroResponse response = resultado.get(0);
        assertEquals(100L, response.getId());
        assertEquals("Cien años de soledad", response.getTitulo());
        assertEquals("9780307474728", response.getIsbn());
        assertEquals(10L, response.getAutorId());
        assertEquals("Gabriel García Márquez", response.getAutorNombre());

        verify(libroRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("obtenerPorId() -> lanza 404 si el libro no existe")
    void obtenerPorId_deberiaLanzar404SiNoExiste() {
        // Arrange
        Long idInexistente = 999L;
        when(libroRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act + Assert
        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> libroService.obtenerPorId(idInexistente)
        );

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());

        verify(libroRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("crear() -> guarda libro y devuelve LibroResponse")
    void crear_deberiaGuardarLibroYDevolverResponse() {
        // Arrange
        LibroCreateRequest request = mock(LibroCreateRequest.class);
        when(request.getIsbn()).thenReturn("99900475");
        when(request.getTitulo()).thenReturn("El Quijote");
        when(request.getAutorId()).thenReturn(5L);

        // Autor existente (lo devuelve autorRepository.findById)
        Autor autor = mock(Autor.class);
        when(autor.getId()).thenReturn(5L);
        when(autor.getNombre()).thenReturn("Miguel de Cervantes");

        // Libro guardado (lo devuelve libroRepository.save)
        Libro guardado = mock(Libro.class);
        when(guardado.getId()).thenReturn(1L);
        when(guardado.getIsbn()).thenReturn("99900475");
        when(guardado.getTitulo()).thenReturn("El Quijote");
        when(guardado.getAutor()).thenReturn(autor); // <- importante (no encadenar sin mockear)

        when(libroRepository.existsByIsbn("99900475")).thenReturn(false);
        when(autorRepository.findById(5L)).thenReturn(Optional.of(autor));
        when(libroRepository.save(any(Libro.class))).thenReturn(guardado);

        // Act
        LibroResponse response = libroService.crear(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("99900475", response.getIsbn());
        assertEquals("El Quijote", response.getTitulo());
        assertEquals(5L, response.getAutorId());
        assertEquals("Miguel de Cervantes", response.getAutorNombre());

        // Verify (las 3 interacciones clave)
        verify(libroRepository, times(1)).existsByIsbn("99900475");
        verify(autorRepository, times(1)).findById(5L);
        verify(libroRepository, times(1)).save(any(Libro.class));
    }

    @Test
    @DisplayName("crear() -> lanza 409 si el ISBN ya existe")
    void crear_deberiaLanzar409SiIsbnYaExiste() {
        // Arrange
        LibroCreateRequest request = mock(LibroCreateRequest.class);
        when(request.getIsbn()).thenReturn("99900475");
        when(request.getTitulo()).thenReturn("El Quijote");
        when(request.getAutorId()).thenReturn(5L);

        // Simulamos que YA existe un libro con ese ISBN
        when(libroRepository.existsByIsbn("99900475")).thenReturn(true);

        // Act + Assert
        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> libroService.crear(request)
        );

        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());

        // (Opcional, pero muy útil) comprobar mensaje
        assertTrue(ex.getReason().contains("Ya existe un libro con ISBN"));

        // Verify
        verify(libroRepository, times(1)).existsByIsbn("99900475");

        // Como falla en el primer if, NO debe seguir
        verify(autorRepository, never()).findById(anyLong());
        verify(libroRepository, never()).save(any(Libro.class));
    }

}