package com.tienda.ms_ventas.controllers;

import com.tienda.ms_ventas.dto.VentaDTO;
import com.tienda.ms_ventas.model.Venta;
import com.tienda.ms_ventas.services.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<Venta> realizarVenta(@Valid @RequestBody VentaDTO dto) {
        // Benyamin Arcapio: Endpoint principal de facturacion
        return new ResponseEntity<>(service.procesarVenta(dto), HttpStatus.CREATED);
    }
}
