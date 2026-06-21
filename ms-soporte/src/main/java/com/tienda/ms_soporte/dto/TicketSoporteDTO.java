package com.tienda.ms_soporte.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TicketSoporteDTO {
    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotBlank(message = "El asunto es obligatorio")
    private String asunto;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    private String descripcion;

    @NotBlank(message = "La prioridad es obligatoria")
    private String prioridad;
}