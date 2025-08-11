// src/main/java/repository/DiarioEnMemoria.java

package repository;

import model.Momento;
import java.util.ArrayList;
import java.util.List;

public class DiarioEnMemoria implements DiarioRepository {

    private List<Momento> momentos;

    public DiarioEnMemoria() {
        this.momentos = new ArrayList<>();
    }

    @Override
    public void addMomento(Momento momento) {
        this.momentos.add(momento);
    }

    @Override
    public List<Momento> getTodosLosMomentos() {
        return new ArrayList<>(this.momentos);
    }
}
