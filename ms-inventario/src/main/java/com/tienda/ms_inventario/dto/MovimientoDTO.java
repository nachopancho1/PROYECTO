package com.tienda.ms_inventario.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class MovimientoDTO {
    @NotNull private Long productoId;
    @NotBlank private String tipoMovimiento; // "ENTRADA" o "SALIDA"
    @Min(1) private int cantidad;
    @NotNull private Long bodegaId;
}