package com.tienda.ms_reclamos.controller;

import com.tienda.ms_reclamos.dto.ReclamoDTO;
import com.tienda.ms_reclamos.model.Reclamo;
import com.tienda.ms_reclamos.services.ReclamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoService service;

    @GetMapping
    public ResponseEntity<List<Reclamo>> listar() {
        return ResponseEntity.ok(service.listarReclamos());
    }

    @PostMapping
    public ResponseEntity<Reclamo> crear(@Valid @RequestBody ReclamoDTO dto) {
        Reclamo nuevo = service.registrarReclamo(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}