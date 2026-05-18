package com.tienda.ms_inventario.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class BodegaDTO {
    @NotBlank private String nombre;
    @NotBlank private String ubicacion;
}