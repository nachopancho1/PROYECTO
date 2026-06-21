package com.tienda.ms_ventas.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class MetodoPagoDTO {
    @NotBlank(message = "El tipo de pago es obligatorio")
    private String tipo; // Cambiado de 'nombre' a 'tipo'
    
    private String comprobante; // Agregado para que puedas mandarlo desde Postman
}