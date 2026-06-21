package com.tienda.ms_productos.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class OfertaDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @Min(value = 1, message = "El descuento mínimo es 1%")
    @Max(value = 100, message = "El descuento máximo es 100%")
    private double porcentajeDescuento;

    @NotBlank(message = "La descripción de la oferta es obligatoria")
    private String descripcion;
}