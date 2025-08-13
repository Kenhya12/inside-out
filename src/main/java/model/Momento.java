// src/main/java/model/Momento.java

package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Momento {

    private static int contadorId = 0;
    
    private int id;
    private String titulo;
    private String descripcion;
    private Emotion emocion;
    private LocalDate fechaSuceso;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    // EL CONSTRUCTOR CORRECTO
    // Su firma debe coincidir con la llamada del controlador:
    // Momento(String, String, Emotion, LocalDate)
    public Momento(String titulo, String descripcion, Emotion emocion, LocalDate fechaSuceso) {
        this.id = ++contadorId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.emocion = emocion;
        this.fechaSuceso = fechaSuceso;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }
    
    // Aquí irían los getters y setters...
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Emotion getEmocion() {
        return emocion;
    }

    public LocalDate getFechaSuceso() {
        return fechaSuceso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFecha() {
        return fechaModificacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        this.fechaModificacion = LocalDateTime.now();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.fechaModificacion = LocalDateTime.now();
    }
    
    public void setEmocion(Emotion emocion) {
        this.emocion = emocion;
        this.fechaModificacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Emoción: " + emocion.getName() + 
        ", Fecha: " + fechaSuceso + ", Descripción: " + descripcion;
    }
}
