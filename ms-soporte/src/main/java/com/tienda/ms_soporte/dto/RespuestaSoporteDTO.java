package com.tienda.ms_soporte.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RespuestaSoporteDTO {
    @NotNull(message = "El ticket es obligatorio")
    private Long ticketId;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
}