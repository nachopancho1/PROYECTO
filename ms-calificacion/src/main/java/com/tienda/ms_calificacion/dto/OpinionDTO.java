package com.tienda.ms_calificacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OpinionDTO {

    @NotBlank(message = "El texto de la opinión no puede estar vacío")
    private String texto;

    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;
}