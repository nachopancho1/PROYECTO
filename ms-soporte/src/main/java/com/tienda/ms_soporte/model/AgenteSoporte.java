package com.tienda.ms_soporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "agentes_soporte")
@Data
public class AgenteSoporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Debe ingresar un correo valido")
    private String email;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TicketSoporte> tickets;
}