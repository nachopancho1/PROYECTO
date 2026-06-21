package com.tienda.ms_reclamos.controller;

import com.tienda.ms_reclamos.dto.ResolucionDTO;
import com.tienda.ms_reclamos.model.Resolucion;
import com.tienda.ms_reclamos.services.ReclamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resoluciones")
public class ResolucionController {

    @Autowired
    private ReclamoService service;

    @GetMapping
    public ResponseEntity<List<Resolucion>> listar() {
        return ResponseEntity.ok(service.listarResoluciones());
    }

    @PostMapping
    public ResponseEntity<Resolucion> crear(@Valid @RequestBody ResolucionDTO dto) {
        Resolucion nuevo = service.registrarResolucion(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}