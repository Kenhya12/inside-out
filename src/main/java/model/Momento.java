package model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Momento {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private final String titulo;
    private final String descripcion;
    private final LocalDateTime fecha;
    private final Emotion emocion;

    public Momento(String titulo, String descripcion, LocalDateTime fecha, Emotion emocion) {
        this.id = counter.incrementAndGet();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.emocion = emocion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Emotion getEmocion() {
        return emocion;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Emoción: " + emocion.getName() + 
        ", Fecha: " + fecha + ", Descripción: " + descripcion;
    }
}