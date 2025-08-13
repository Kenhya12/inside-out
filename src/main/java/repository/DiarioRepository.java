// src/main/java/repository/DiarioRepository.java

package repository;

import model.Emotion;
import model.Momento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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