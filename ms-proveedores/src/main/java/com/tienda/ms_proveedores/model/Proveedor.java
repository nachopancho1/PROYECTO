package com.tienda.ms_proveedores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
@Entity
@Table(name = "proveedores")
@Data
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    // ESTO ES LO QUE FALTA:
    @ManyToOne
    @JoinColumn(name = "suministro_id")
    private Suministro suministro; 

    @OneToOne(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private Contacto contacto;
}