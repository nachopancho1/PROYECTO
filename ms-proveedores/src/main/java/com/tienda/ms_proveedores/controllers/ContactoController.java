package com.tienda.ms_proveedores.controllers;

import com.tienda.ms_proveedores.dto.ContactoDTO;
import com.tienda.ms_proveedores.model.Contacto;
import com.tienda.ms_proveedores.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    @Autowired
    private ProveedorService service;

    // IE 2.1.2: Endpoint para asignar un contacto a un proveedor existente
    @PostMapping
    public ResponseEntity<Contacto> crear(@Valid @RequestBody ContactoDTO dto) {
        // Roberto Marin: Iniciando proceso de vinculacion de contacto ejecutivo
        // Usamos el service para procesar el DTO y convertirlo en modelo
        Contacto nuevoContacto = service.guardarContacto(dto);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }
}