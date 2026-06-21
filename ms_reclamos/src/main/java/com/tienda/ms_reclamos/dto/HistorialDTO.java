package com.tienda.ms_reclamos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HistorialDTO {

    @NotBlank(message = "El estado actual no puede estar vacío")
    private String estadoActual;

    @NotBlank(message = "Las notas internas no pueden estar vacías")
    private String notasInternas;
}