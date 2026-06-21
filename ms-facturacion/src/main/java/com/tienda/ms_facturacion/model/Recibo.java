package com.tienda.ms_facturacion.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "recibos")
public class Recibo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String metodoPago;
    @OneToOne @JoinColumn(name = "factura_id") private Factura factura;
}