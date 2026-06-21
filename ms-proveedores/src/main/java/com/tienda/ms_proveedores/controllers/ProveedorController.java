package com.tienda.ms_proveedores.controllers;

import com.tienda.ms_proveedores.dto.ProveedorDTO;
import com.tienda.ms_proveedores.model.Proveedor;
import com.tienda.ms_proveedores.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() {
        return ResponseEntity.ok(service.listarProveedores());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@Valid @RequestBody ProveedorDTO dto) {
        return new ResponseEntity<>(service.registrarProveedor(dto), HttpStatus.CREATED);
    }
}