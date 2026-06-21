package com.tienda.ms_inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    private String tipoMovimiento; // ENTRADA o SALIDA
    private LocalDateTime fechaActualizacion = LocalDateTime.now();
}