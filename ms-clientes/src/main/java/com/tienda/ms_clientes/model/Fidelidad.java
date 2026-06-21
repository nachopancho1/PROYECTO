package com.tienda.ms_clientes.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fidelidad")
@Data
public class Fidelidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntos;
    private String nivel; // Ejemplo: "Bronce", "Plata", "Oro"
    private String fechaUltimaActualizacion;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;
}