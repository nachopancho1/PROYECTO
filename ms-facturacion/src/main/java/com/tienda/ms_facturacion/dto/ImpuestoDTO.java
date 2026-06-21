package com.tienda.ms_facturacion.dto;
import lombok.Data;

@Data public class ImpuestoDTO {
    private Long id;
    private String tipo;
    private Double porcentaje;
}
