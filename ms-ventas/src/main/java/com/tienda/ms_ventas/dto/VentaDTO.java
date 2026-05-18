package com.tienda.ms_ventas.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Data
public class VentaDTO {
    @NotNull private Long clienteId; // ID del cliente del otro micro
    @NotNull private Long metodoPagoId;
    @NotEmpty private List<DetalleVentaDTO> detalles;
}