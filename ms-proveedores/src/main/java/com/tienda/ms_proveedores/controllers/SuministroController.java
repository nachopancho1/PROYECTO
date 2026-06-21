package com.tienda.ms_proveedores.controllers;

import com.tienda.ms_proveedores.dto.SuministroDTO;
import com.tienda.ms_proveedores.model.Suministro;
import com.tienda.ms_proveedores.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suministros")
public class SuministroController {

    @Autowired
    private ProveedorService service;

    @PostMapping
    public ResponseEntity<Suministro> crear(@Valid @RequestBody SuministroDTO dto) {
        return new ResponseEntity<>(service.guardarSuministro(dto), HttpStatus.CREATED);
    }
}