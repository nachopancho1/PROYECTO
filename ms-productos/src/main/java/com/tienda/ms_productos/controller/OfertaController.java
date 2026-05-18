package com.tienda.ms_productos.controller;

import com.tienda.ms_productos.dto.OfertaDTO; // El DTO que creamos
import com.tienda.ms_productos.model.Oferta;
import com.tienda.ms_productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private ProductoService service;

    // Listar todas las ofertas
    @GetMapping
    public ResponseEntity<List<Oferta>> listar() {
        // Roberto Marin: Consultando promociones vigentes
        return ResponseEntity.ok(service.listarOfertas());
    }

    // Crear oferta usando DTO
    @PostMapping
    public ResponseEntity<Oferta> crear(@Valid @RequestBody OfertaDTO dto) {
        // Benyamin Arcapio: Registrando nueva oferta para producto ID: dto.getProductoId()
        return new ResponseEntity<>(service.crearOferta(dto), HttpStatus.CREATED);
    }
}