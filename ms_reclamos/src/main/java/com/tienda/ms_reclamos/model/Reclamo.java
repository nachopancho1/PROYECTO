package com.tienda.ms_reclamos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "reclamos")
@Data
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asunto;
    private String detalle;
    private LocalDate fechaRegistro;
    private String categoria; 
}