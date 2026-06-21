package com.tienda.ms_calificacion.controller;

import com.tienda.ms_calificacion.dto.OpinionDTO; 
import com.tienda.ms_calificacion.model.Opinion;
import com.tienda.ms_calificacion.services.CalificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/opinion")
public class OpinionController {

    @Autowired
    private CalificacionService service;

    @GetMapping
    public ResponseEntity<List<Opinion>> listar() {
        // Roberto Marin: Trazabilidad al listar todas las opiniones de usuarios
        return ResponseEntity.ok(service.listarOpiniones());
    }

    @PostMapping
    public ResponseEntity<Opinion> crear(@Valid @RequestBody OpinionDTO dto) { 
        // Benyamin Arcapio: Se guarda la opinion y se procesa mediante el DTO correspondiente
        Opinion nuevo = service.registrarOpinion(dto); 
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}