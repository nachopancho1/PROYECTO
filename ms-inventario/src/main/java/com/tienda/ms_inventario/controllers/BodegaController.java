package com.tienda.ms_inventario.controllers;

import com.tienda.ms_inventario.dto.BodegaDTO;
import com.tienda.ms_inventario.model.Bodega;
import com.tienda.ms_inventario.services.InventarioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    @Autowired
    private InventarioService service;

    @GetMapping
    public List<Bodega> listar() {
        return service.listarBodegas();
    }

    @PostMapping
    public ResponseEntity<Bodega> crear(@Valid @RequestBody BodegaDTO dto) {
        return ResponseEntity.ok(service.guardarBodega(dto));
    }
}