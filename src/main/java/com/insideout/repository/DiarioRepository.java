// src/main/java/repository/DiarioRepository.java

package com.insideout.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.insideout.model.Emotion;
import com.insideout.model.Momento;

public interface DiarioRepository {
    void addMomento(Momento momento);
    List<Momento> getTodosLosMomentos();
    boolean eliminarMomento(int id);
    Optional<Momento> buscarMomentoPorId(int id);
    List<Momento> getMomentosByEmocion(Emotion emocion);
    
    List<Momento> getMomentosByFecha(LocalDateTime fecha);
    List<Momento> getMomentosByFecha(int dia, int mes, int anio);
    List<Momento> getMomentosByMesAndAnio(int mes, int anio);
    
}