package com.tienda.ms_facturacion.dto;
import lombok.Data;

@Data public class ReciboDTO {
    private Long id;
    private String metodoPago;
    private Long facturaId;
}