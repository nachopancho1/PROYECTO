package com.tienda.ms_pedidos.dto;
import lombok.Data;

@Data public class EnvioDTO {
    private Long id;
    private String direccionEnvio;
    private Long pedidoId;
}