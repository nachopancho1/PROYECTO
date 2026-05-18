package com.tienda.ms_productos.controller;

import com.tienda.ms_productos.dto.ProductoDTO; // Importante
import com.tienda.ms_productos.model.Producto;
import com.tienda.ms_productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    // IE 2.1.2: Endpoint para listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        // Roberto Marin: Retornando lista de productos desde XAMPP
        return ResponseEntity.ok(service.listarProductos());
    }

    // IE 2.1.2: Endpoint para crear un producto con validación usando DTO
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody ProductoDTO dto) {
        // Benyamin Arcapio: Recibiendo DTO para procesar el registro
        Producto nuevo = service.guardarProducto(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // Endpoint para activar la oferta
    @PutMapping("/aplicar-oferta/{ofertaId}")
    public ResponseEntity<Producto> activarOferta(@PathVariable Long ofertaId) {
        // Roberto Marin: Ejecutando recalculo de precio por oferta
        Producto productoModificado = service.aplicarOfertaExistenteAProducto(ofertaId);
        return ResponseEntity.ok(productoModificado);
    }
}