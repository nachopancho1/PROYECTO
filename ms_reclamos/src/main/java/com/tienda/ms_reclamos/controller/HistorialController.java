package com.tienda.ms_reclamos.controller;

import com.tienda.ms_reclamos.dto.HistorialDTO;
import com.tienda.ms_reclamos.model.Historial;
import com.tienda.ms_reclamos.services.ReclamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private ReclamoService service;

    @GetMapping
    public ResponseEntity<List<Historial>> listar() {
        return ResponseEntity.ok(service.listarHistorial());
    }

    @PostMapping
    public ResponseEntity<Historial> crear(@Valid @RequestBody HistorialDTO dto) {
        Historial nuevo = service.registrarHistorial(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}