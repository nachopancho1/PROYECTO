package com.tienda.ms_inventario.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InventarioDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @Min(value = 0, message = "La cantidad debe ser mayor o igual a cero")
    private int cantidad;

    @NotBlank(message = "El tipo de movimiento es necesario (ENTRADA/SALIDA)")
    private String tipoMovimiento;
}