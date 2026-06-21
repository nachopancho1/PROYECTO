package com.tienda.ms_inventario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId; // ID del producto que viene de ms-productos
    private int cantidad;
    private String tipoMovimiento; // "ENTRADA" o "SALIDA"
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;
}