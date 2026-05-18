package com.tienda.ms_inventario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bodegas")
@Data
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: Bodega Central, Pasillo A
    private String ubicacion;

    @OneToMany(mappedBy = "bodega")
    @JsonIgnore
    private List<Movimiento> movimientos;
}