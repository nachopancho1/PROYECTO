package com.tienda.ms_facturacion.dto;
import lombok.Data;

@Data public class FacturaDTO {
    private Long id;
    private String numeroFactura;
    private Double total;
}