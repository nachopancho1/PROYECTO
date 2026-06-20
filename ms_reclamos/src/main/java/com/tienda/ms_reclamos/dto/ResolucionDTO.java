package com.tienda.ms_reclamos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResolucionDTO {

    @NotBlank(message = "La respuesta ofrecida no puede estar vacía")
    private String respuestaOfrecida;

    private boolean requiereCompensacion;
}