package com.tienda.ms_ventas.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "metodos_pago")
@Data
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ejemplo: efectivo, tarjeta, transferencia
    private String comprobante; // Número de transacción o folio

    @OneToOne
    @JoinColumn(name = "venta_id")
    @JsonIgnore
    private Venta venta;
}