package com.tienda.ms_ventas.controllers;

import com.tienda.ms_ventas.dto.MetodoPagoDTO;
import com.tienda.ms_ventas.model.MetodoPago;
import com.tienda.ms_ventas.services.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private VentaService service;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> listar() {
        // Roberto Marin: Listando opciones de pago para el checkout
        return ResponseEntity.ok(service.listarMetodosPago());
    }

    @PostMapping
    public ResponseEntity<MetodoPago> crear(@Valid @RequestBody MetodoPagoDTO dto) {
        // Benyamin Arcapio: Registro de nuevo metodo de pago validado
        return new ResponseEntity<>(service.guardarMetodoPago(dto), HttpStatus.CREATED);
    }
}