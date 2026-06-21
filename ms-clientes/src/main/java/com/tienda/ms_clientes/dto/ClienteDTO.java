package com.tienda.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Email invalido")
    private String email; 
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;
}