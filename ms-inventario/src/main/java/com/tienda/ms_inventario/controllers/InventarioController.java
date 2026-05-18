package com.tienda.ms_inventario.controllers;

import com.tienda.ms_inventario.dto.MovimientoDTO; // Importante
import com.tienda.ms_inventario.model.Movimiento;
import com.tienda.ms_inventario.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService service;

   
    @GetMapping("/movimientos")
    public ResponseEntity<List<Movimiento>> listar() {
        // Roberto Marin: Retornando historial completo desde XAMPP
        return ResponseEntity.ok(service.listarMovimientos());
    }

    // IE 2.4.2: Registro de movimiento usando DTO para validación
    @PostMapping("/movimientos")
    public ResponseEntity<Movimiento> crear(@Valid @RequestBody MovimientoDTO dto) {
        // Benyamin Arcapio: Recibiendo DTO y enviandolo al Service para mapeo
        return new ResponseEntity<>(service.registrarMovimiento(dto), HttpStatus.CREATED);
    }
    
    // Métodos adicionales para los contadores (Rúbrica)
    @GetMapping("/stats/entradas")
    public ResponseEntity<Long> obtenerEntradas() {
        return ResponseEntity.ok(service.obtenerCantidadEntradas());
    }

    @GetMapping("/stats/salidas")
    public ResponseEntity<Long> obtenerSalidas() {
        return ResponseEntity.ok(service.obtenerCantidadSalidas());
    }
}