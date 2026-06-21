package com.tienda.ms_soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.ms_soporte.dto.TicketSoporteDTO;
import com.tienda.ms_soporte.model.TicketSoporte;
import com.tienda.ms_soporte.services.SoporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketSoporteController {

    @Autowired
    private SoporteService service;

    @GetMapping
    public ResponseEntity<List<TicketSoporte>> listar() {
        return ResponseEntity.ok(service.listarTickets());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<TicketSoporte>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.listarTicketsPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<TicketSoporte> crear(@Valid @RequestBody TicketSoporteDTO dto) {
        TicketSoporte nuevo = service.crearTicket(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{ticketId}/asignar/{agenteId}")
    public ResponseEntity<TicketSoporte> asignarAgente(@PathVariable Long ticketId, @PathVariable Long agenteId) {
        TicketSoporte ticket = service.asignarAgente(ticketId, agenteId);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticketId}/cerrar")
    public ResponseEntity<TicketSoporte> cerrar(@PathVariable Long ticketId) {
        TicketSoporte ticket = service.cerrarTicket(ticketId);
        return ResponseEntity.ok(ticket);
    }
}