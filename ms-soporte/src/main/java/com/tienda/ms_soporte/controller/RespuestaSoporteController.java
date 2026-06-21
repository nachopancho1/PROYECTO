package com.tienda.ms_soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.ms_soporte.dto.RespuestaSoporteDTO;
import com.tienda.ms_soporte.model.RespuestaSoporte;
import com.tienda.ms_soporte.services.SoporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/respuestas-soporte")
public class RespuestaSoporteController {

    @Autowired
    private SoporteService service;

    @GetMapping
    public ResponseEntity<List<RespuestaSoporte>> listar() {
        return ResponseEntity.ok(service.listarRespuestas());
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<RespuestaSoporte>> listarPorTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(service.listarRespuestasPorTicket(ticketId));
    }

    @PostMapping
    public ResponseEntity<RespuestaSoporte> crear(@Valid @RequestBody RespuestaSoporteDTO dto) {
        RespuestaSoporte nueva = service.guardarRespuesta(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }
}