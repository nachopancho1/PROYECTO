package com.tienda.ms_productos.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ofertas")
@Data
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double porcentajeDescuento;
    
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference // Evita que la oferta intente mostrar el producto de vuelta
    private Producto producto;
}