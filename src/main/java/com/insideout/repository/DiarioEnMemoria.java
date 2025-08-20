// src/main/java/repository/DiarioEnMemoria.java

package com.insideout.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

import com.insideout.model.Emotion;
import com.insideout.model.Momento;

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

    @Override
    public List<Momento> getMomentosByFecha(LocalDateTime fecha) {
        return this.momentos.stream()
                .filter(momento -> momento.getFecha().toLocalDate().isEqual(fecha.toLocalDate()))
                .collect(Collectors.toList());
    }

@Override
public List<Momento> getMomentosByFecha(int dia, int mes, int anio) {
    return momentos.stream()
            .filter(m -> m.getFecha().getDayOfMonth() == dia &&
                        m.getFecha().getMonthValue() == mes &&
                        m.getFecha().getYear() == anio)
            .collect(Collectors.toList());
}

    @Override
    public List<Momento> getMomentosByMesAndAnio(int mes, int anio) {
        return this.momentos.stream()
                .filter(momento -> momento.getFecha().getMonthValue() == mes && 
                momento.getFecha().getYear() == anio)
                .collect(Collectors.toList());
    }
}
