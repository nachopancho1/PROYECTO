package com.tienda.ms_pedidos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "detalles_pedido")
public class DetallePedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private Long productoId;
    @Column(nullable = false) private Integer cantidad;
    @ManyToOne @JoinColumn(name = "pedido_id") private Pedido pedido;
}