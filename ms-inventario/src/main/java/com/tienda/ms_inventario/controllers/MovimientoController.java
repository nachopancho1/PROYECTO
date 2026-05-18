package com.tienda.ms_inventario.controllers;

import com.tienda.ms_inventario.dto.MovimientoDTO;
import com.tienda.ms_inventario.model.Movimiento;
import com.tienda.ms_inventario.services.InventarioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private InventarioService service;

    @GetMapping
    public List<Movimiento> listar() {
        return service.listarMovimientos();
    }

    @PostMapping
    public ResponseEntity<Movimiento> crear(@Valid @RequestBody MovimientoDTO dto) {
        return new ResponseEntity<>(service.registrarMovimiento(dto), HttpStatus.CREATED);
    }

    // Para obtener el contador de entradas
    @GetMapping("/contador/entradas")
    public ResponseEntity<Long> getContadorEntradas() {
        long totalEntradas = service.obtenerCantidadEntradas();
        return ResponseEntity.ok(totalEntradas); // Retorna 200 OK con el número
    }

    // Lo mismo otra vez pero con salidas sjs
    @GetMapping("/contador/salidas")
    public ResponseEntity<Long> getContadorSalidas() {
        long totalSalidas = service.obtenerCantidadSalidas();
        return ResponseEntity.ok(totalSalidas); // Retorna 200 OK con el número
    }
}