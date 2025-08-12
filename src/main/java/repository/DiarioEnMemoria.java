// src/main/java/repository/DiarioEnMemoria.java

package repository;

import model.Emotion;
import model.Momento;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

public class DiarioEnMemoria implements DiarioRepository {
    private final List<Momento> momentos;

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

    @Override
    public boolean eliminarMomento(int id) {
        Iterator<Momento> iterator = this.momentos.iterator();
        while (iterator.hasNext()) {
            Momento momento = iterator.next();
            if (momento.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Momento> buscarMomentoPorId(int id) {
        for (Momento momento : this.momentos) {
            if (momento.getId() == id) {
                return Optional.of(momento);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Momento> getMomentosByEmocion(Emotion emocion) {
        return this.momentos.stream()
                .filter(momento -> momento.getEmocion() == emocion)
                .collect(Collectors.toList());
    }
}
