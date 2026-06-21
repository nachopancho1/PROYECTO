package com.tienda.ms_soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.ms_soporte.dto.AgenteSoporteDTO;
import com.tienda.ms_soporte.model.AgenteSoporte;
import com.tienda.ms_soporte.services.SoporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agentes-soporte")
public class AgenteSoporteController {

    @Autowired
    private SoporteService service;

    @GetMapping
    public ResponseEntity<List<AgenteSoporte>> listar() {
        return ResponseEntity.ok(service.listarAgentes());
    }

    @PostMapping
    public ResponseEntity<AgenteSoporte> crear(@Valid @RequestBody AgenteSoporteDTO dto) {
        AgenteSoporte nuevo = service.guardarAgente(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}