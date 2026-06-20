package com.tienda.ms_reclamos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReclamoDTO {

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "El detalle no puede estar vacío")
    private String detalle;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;
}