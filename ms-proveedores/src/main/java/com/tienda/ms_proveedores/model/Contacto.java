package com.tienda.ms_proveedores.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contactos")
@Data
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEjecutivo;
    private String email;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "proveedor_id")
    @JsonIgnore
    private Proveedor proveedor;
}