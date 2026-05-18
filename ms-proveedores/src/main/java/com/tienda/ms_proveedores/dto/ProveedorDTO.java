package com.tienda.ms_proveedores.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ProveedorDTO {

    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @NotNull(message = "Debe asignar un suministro base")
    private Long suministroId;
}