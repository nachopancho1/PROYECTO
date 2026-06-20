package com.tienda.ms_reclamos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "historial_estados")
@Data
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estadoActual;
    private LocalDate fechaActualizacion;
    private String notasInternas;
}