package com.tienda.ms_proveedores.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class SuministroDTO {
    @NotBlank(message = "El nombre del suministro es obligatorio")
    private String nombre;
}