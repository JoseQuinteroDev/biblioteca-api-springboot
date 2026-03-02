package com.josequintero.biblioteca.service;

import com.josequintero.biblioteca.dto.request.AutorCreateRequest;
import com.josequintero.biblioteca.dto.response.AutorResponse;
import com.josequintero.biblioteca.model.Autor;
import com.josequintero.biblioteca.repository.AutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    @Test
    @DisplayName("crear() -> guarda un autor y devuelve AutorResponse")
    void crear_deberiaGuardarAutorYDevolverResponse() {
        // Arrange (preparar)
        AutorCreateRequest request = mock(AutorCreateRequest.class);
        when(request.getNombre()).thenReturn("Gabriel García Márquez");
        when(request.getPais()).thenReturn("Colombia");

        Autor guardado = mock(Autor.class);
        when(guardado.getId()).thenReturn(1L);
        when(guardado.getNombre()).thenReturn("Gabriel García Márquez");
        when(guardado.getPais()).thenReturn("Colombia");

        when(autorRepository.save(any(Autor.class))).thenReturn(guardado);

        // Act (ejecutar)
        AutorResponse response = autorService.crear(request);

        // Assert (comprobar)
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Gabriel García Márquez", response.getNombre());
        assertEquals("Colombia", response.getPais());

        // Verificamos que se llamó a save() una vez
        verify(autorRepository, times(1)).save(any(Autor.class));

        // Capturamos el Autor que se envió al repositorio para comprobar su contenido
        ArgumentCaptor<Autor> captor = ArgumentCaptor.forClass(Autor.class);
        verify(autorRepository).save(captor.capture());

        Autor autorEnviadoASave = captor.getValue();
        assertEquals("Gabriel García Márquez", autorEnviadoASave.getNombre());
        assertEquals("Colombia", autorEnviadoASave.getPais());
    }

    @Test
    @DisplayName("listar() -> devuelve la lista de autores mapeada a AutorResponse")
    void listar_deberiaDevolverListaDeAutoresResponse() {
        // Arrange
        Autor autor1 = mock(Autor.class);
        when(autor1.getId()).thenReturn(1L);
        when(autor1.getNombre()).thenReturn("Autor 1");
        when(autor1.getPais()).thenReturn("España");

        Autor autor2 = mock(Autor.class);
        when(autor2.getId()).thenReturn(2L);
        when(autor2.getNombre()).thenReturn("Autor 2");
        when(autor2.getPais()).thenReturn("México");

        when(autorRepository.findAll()).thenReturn(List.of(autor1, autor2));

        // Act
        List<AutorResponse> resultado = autorService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals(1L, resultado.get(0).getId());
        assertEquals("Autor 1", resultado.get(0).getNombre());
        assertEquals("España", resultado.get(0).getPais());

        assertEquals(2L, resultado.get(1).getId());
        assertEquals("Autor 2", resultado.get(1).getNombre());
        assertEquals("México", resultado.get(1).getPais());

        verify(autorRepository, times(1)).findAll();
    }
}