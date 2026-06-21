package com.tienda.ms_calificacion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalificacionDTO {

    @NotNull(message = "El puntaje no puede ser nulo")
    @Min(value = 1, message = "El puntaje mínimo es 1")
    @Max(value = 5, message = "El puntaje máximo es 5")
    private Integer puntaje;

    @NotBlank(message = "El comentario no puede estar vacío")
    private String comentario;
}