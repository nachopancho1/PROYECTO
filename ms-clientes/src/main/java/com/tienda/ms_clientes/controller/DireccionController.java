package com.tienda.ms_clientes.controller;

import com.tienda.ms_clientes.dto.DireccionDTO;
import com.tienda.ms_clientes.model.Direccion;
import com.tienda.ms_clientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Direccion> crear(@Valid @RequestBody DireccionDTO dto) {
        return ResponseEntity.ok(service.agregarDireccion(dto));
    }
}