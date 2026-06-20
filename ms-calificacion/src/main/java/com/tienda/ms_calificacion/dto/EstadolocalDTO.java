package com.tienda.ms_calificacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstadolocalDTO {

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
}