package com.insideout.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import com.insideout.model.Momento;
import com.insideout.model.Emotion;
import com.insideout.repository.DiarioRepository;
import com.insideout.view.ConsolaView;
import com.insideout.controller.MomentoController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

public class MomentoControllerTest {

    private DiarioRepository diarioRepositoryMock;
    private ConsolaView consolaViewMock;
    private MomentoController controller;

    @BeforeEach
    public void setUp() {
        diarioRepositoryMock = Mockito.mock(DiarioRepository.class);
        consolaViewMock = Mockito.mock(ConsolaView.class);
        controller = new MomentoController(diarioRepositoryMock, consolaViewMock);

        // Configuramos un comportamiento por defecto para los mocks
        when(consolaViewMock.solicitarId()).thenReturn(1);
        when(consolaViewMock.solicitarTitulo()).thenReturn("Mi momento feliz");
        when(consolaViewMock.solicitarDescripcion()).thenReturn("Un día genial");
        when(consolaViewMock.solicitarFecha()).thenReturn(LocalDateTime.now());
        when(consolaViewMock.solicitarEmocion()).thenReturn(Emotion.ALEGRIA);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(diarioRepositoryMock);
        Mockito.reset(consolaViewMock);
    }

    @Test
    public void testCrearNuevoMomento() {
        controller.crearNuevoMomento();
        verify(consolaViewMock).solicitarTitulo();
        verify(consolaViewMock).solicitarDescripcion();
        verify(consolaViewMock).solicitarFecha();
        verify(consolaViewMock).solicitarEmocion();
        verify(diarioRepositoryMock).addMomento(any(Momento.class));
        verify(consolaViewMock).mostrarMensajeExito(anyString());
    }

    @Test
    public void testVerTodosLosMomentos() {
        Momento momento = new Momento(1, "Día soleado", "Clima perfecto", LocalDateTime.now(), Emotion.ALEGRIA);
        List<Momento> momentos = List.of(momento);
        when(diarioRepositoryMock.getTodosLosMomentos()).thenReturn(momentos);

        controller.verTodosLosMomentos();

        verify(diarioRepositoryMock).getTodosLosMomentos();
        verify(consolaViewMock).mostrarMomentos(anyList());
    }

    @Test
    public void testEliminarMomento() {
        // Simular que el repositorio devuelve una lista de momentos no vacía
        List<Momento> momentosConId = List.of(new Momento(1, "Día feliz", "Todo va bien", LocalDateTime.now(), Emotion.ALEGRIA));
        when(diarioRepositoryMock.getTodosLosMomentos()).thenReturn(momentosConId);
    
        int momentoId = 1;
        when(consolaViewMock.solicitarId()).thenReturn(momentoId);
        when(diarioRepositoryMock.eliminarMomento(momentoId)).thenReturn(true);

        controller.eliminarMomento();

        // Verificamos que se llamó a solicitarId() después de que el repositorio devolvió una lista no vacía
        verify(consolaViewMock).solicitarId();
        verify(diarioRepositoryMock).eliminarMomento(momentoId);
        verify(consolaViewMock).mostrarMensajeExito(anyString());
    }
}