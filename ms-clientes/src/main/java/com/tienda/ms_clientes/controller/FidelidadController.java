package com.tienda.ms_clientes.controller;

import com.tienda.ms_clientes.dto.FidelidadDTO;
import com.tienda.ms_clientes.model.Fidelidad;
import com.tienda.ms_clientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fidelidad")
public class FidelidadController {

    @Autowired
    private ClienteService service;

    // Listar todos los perfile
    @GetMapping
    public ResponseEntity<List<Fidelidad>> listarTodos() {
        List<Fidelidad> lista = service.listarClientes().stream()
                .map(c -> c.getFidelidad())
                .filter(f -> f != null) // Evitamos nulos por si algún cliente no tiene fidelidad
                .toList();
        return ResponseEntity.ok(lista);
    }

    // Consultar puntos de un cliente específico
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Fidelidad> consultarPorCliente(@PathVariable Long clienteId) {
        // Roberto Marin: El Service ya maneja la excepción, el GlobalExceptionHandler se encarga del resto
        return ResponseEntity.ok(service.consultarPuntos(clienteId));
    }

    //actualizar o registrar fidelidad usando DTO
    @PostMapping
    public ResponseEntity<Fidelidad> guardar(@Valid @RequestBody FidelidadDTO dto) {
        // Benyamin Arcapio: Registro manual de puntos desde el cliente
        Fidelidad nueva = service.actualizarFidelidad(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }
}