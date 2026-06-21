package com.tienda.ms_facturacion.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "facturas")
public class Factura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String numeroFactura;
    @Column(nullable = false) private Double total;
}