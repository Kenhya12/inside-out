package com.insideout.dto;

/**
 * Clase DTO (Data Transfer Object) para el manejo de momentos.
 * Se utiliza para transferir datos de un momento entre capas de la aplicación.
 */

public class MomentoDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String emocion;

    // Constructor vacío
    public MomentoDTO() {
    }

    // Getters y setters para cada campo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }
}
