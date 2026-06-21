package com.tienda.ms_productos.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ProductoDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precioBase;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Long categoriaId;
}