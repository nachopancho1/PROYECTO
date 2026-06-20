package com.tienda.ms_reclamos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "resoluciones")
@Data
public class Resolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String respuestaOfrecida; 
    private LocalDate fechaResolucion;
    private boolean requiereCompensacion;
}