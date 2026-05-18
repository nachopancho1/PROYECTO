package com.tienda.ms_clientes.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class DireccionDTO {

    @NotBlank(message = "La calle es obligatoria")
    private String calle;

    @NotBlank(message = "El numero es obligatorio")
    private String numero;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "el tipo es obligatorio")
    private String tipo;


    @NotNull(message = "El ID del cliente es necesario")
    private Long clienteId;
}