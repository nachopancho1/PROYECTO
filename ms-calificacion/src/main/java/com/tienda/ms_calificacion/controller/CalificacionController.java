package com.tienda.ms_calificacion.controller;

import com.tienda.ms_calificacion.dto.CalificacionDTO; 
import com.tienda.ms_calificacion.model.Calificacion;
import com.tienda.ms_calificacion.services.CalificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calificacion")
public class CalificacionController {

    @Autowired
    private CalificacionService service;

    @GetMapping
    public ResponseEntity<List<Calificacion>> listar() {
        return ResponseEntity.ok(service.listarCalificaciones());
    }

    @PostMapping
    public ResponseEntity<Calificacion> crear(@Valid @RequestBody CalificacionDTO dto) { 
        Calificacion nuevo = service.registrarCalificacion(dto); 
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}