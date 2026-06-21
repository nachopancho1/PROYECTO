package com.tienda.ms_facturacion.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "impuestos")
public class Impuesto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String tipo; // Ej: "IVA"
    @Column(nullable = false) private Double porcentaje;
}