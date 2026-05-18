package com.tienda.ms_clientes.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class FidelidadDTO {
    
    @PositiveOrZero(message = "Los puntos no pueden ser negativos")
    private int puntos;

    @NotBlank(message = "El nivel es obligatorio (Bronce, Plata, Oro)")
    private String nivel;

    private String fechaUltimaActualizacion;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
}