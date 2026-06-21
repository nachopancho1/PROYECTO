package com.tienda.ms_proveedores.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ContactoDTO {
    @NotBlank(message = "El nombre del ejecutivo es obligatorio")
    private String nombreEjecutivo;
    
    @Email(message = "Debe ser un email válido")
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
    
    @NotNull(message = "Debe vincular un proveedor")
    private Long proveedorId;
}