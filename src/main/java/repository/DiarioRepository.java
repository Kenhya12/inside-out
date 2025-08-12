// src/main/java/repository/DiarioRepository.java

package repository;

import model.Momento;
import java.util.List;
import java.util.Optional;

public interface DiarioRepository {
    void addMomento(Momento momento);
    List<Momento> getTodosLosMomentos();
    boolean eliminarMomento(int id);
    Optional<Momento> buscarMomentoPorId(int id);
}