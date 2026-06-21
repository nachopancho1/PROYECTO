package com.tienda.ms_soporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas_soporte")
@Data
public class RespuestaSoporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    private LocalDateTime fechaRespuesta = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketSoporte ticket;
}