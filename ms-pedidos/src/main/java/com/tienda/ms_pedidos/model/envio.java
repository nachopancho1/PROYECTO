package com.tienda.ms_pedidos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "envios")
public class envio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String direccionEnvio;
    @OneToOne @JoinColumn(name = "pedido_id") private Pedido pedido;
}