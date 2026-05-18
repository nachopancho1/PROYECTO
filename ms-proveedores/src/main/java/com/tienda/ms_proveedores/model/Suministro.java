package com.tienda.ms_proveedores.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "suministros")
@Data
public class Suministro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Ej: Frutas, Lácteos

    @OneToMany(mappedBy = "suministro") // Este nombre debe ser igual al de la clase Proveedor
    private List<Proveedor> proveedores;
}