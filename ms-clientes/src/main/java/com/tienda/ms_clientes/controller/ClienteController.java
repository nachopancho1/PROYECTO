package com.tienda.ms_clientes.controller;

import com.tienda.ms_clientes.dto.ClienteDTO; 
import com.tienda.ms_clientes.model.Cliente;
import com.tienda.ms_clientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        // Roberto Marin: Trazabilidad en el endpoint de lectura
        return ResponseEntity.ok(service.listarClientes());
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteDTO dto) { 
        // Benyamin Arcapio: Recibimos el DTO y el Service se encarga del mapeo
        Cliente nuevo = service.registrarCliente(dto); 
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}