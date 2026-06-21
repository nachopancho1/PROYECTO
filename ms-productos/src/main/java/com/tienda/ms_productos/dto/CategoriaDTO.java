package com.tienda.ms_productos.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CategoriaDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
}