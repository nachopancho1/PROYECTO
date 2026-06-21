package com.tienda.ms_calificacion.controller;

import com.tienda.ms_calificacion.dto.EstadolocalDTO; 
import com.tienda.ms_calificacion.model.Estadolocal;
import com.tienda.ms_calificacion.services.CalificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estado-local")
public class EstadolocalController {

    @Autowired
    private CalificacionService service;

    @GetMapping
    public ResponseEntity<List<Estadolocal>> listar() {
        return ResponseEntity.ok(service.listarEstadosLocales());
    }

    @PostMapping
    public ResponseEntity<Estadolocal> crear(@Valid @RequestBody EstadolocalDTO dto) { 
        Estadolocal nuevo = service.registrarEstadoLocal(dto); 
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}