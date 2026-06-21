package com.tienda.ms_ventas.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class DetalleVentaDTO {
    @NotNull private Long productoId;
    @Min(1) private int cantidad;
}