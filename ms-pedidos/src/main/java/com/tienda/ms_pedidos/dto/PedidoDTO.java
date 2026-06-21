package com.tienda.ms_pedidos.dto;
import lombok.Data;

@Data public class PedidoDTO {
    private Long id;
    private String codigo;
    private Long clienteId;
}