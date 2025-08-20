package com.insideout.mapper;

import java.time.format.DateTimeFormatter;

import com.insideout.dto.MomentoDTO;
import com.insideout.model.Momento;

public class MomentoMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static MomentoDTO toDTO(Momento momento) {
        MomentoDTO dto = new MomentoDTO();
        dto.setId(momento.getId());
        dto.setTitulo(momento.getTitulo());
        dto.setDescripcion(momento.getDescripcion());
        dto.setFecha(momento.getFecha().format(FORMATTER)); 
        dto.setEmocion(momento.getEmocion().getName());
        return dto;
    }
}
