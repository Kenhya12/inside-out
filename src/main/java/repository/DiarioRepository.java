// src/main/java/repository/DiarioRepository.java

package repository;

import model.Momento;
import java.util.List;

public interface DiarioRepository {
    void addMomento(Momento momento);
    List<Momento> getTodosLosMomentos();
}