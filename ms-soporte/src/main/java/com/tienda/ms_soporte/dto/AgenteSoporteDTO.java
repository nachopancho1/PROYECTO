package com.tienda.ms_soporte.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AgenteSoporteDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;
}