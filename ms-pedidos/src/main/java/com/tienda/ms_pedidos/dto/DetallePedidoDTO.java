package com.tienda.ms_pedidos.dto;
import lombok.Data;

@Data public class DetallePedidoDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Long pedidoId;
}