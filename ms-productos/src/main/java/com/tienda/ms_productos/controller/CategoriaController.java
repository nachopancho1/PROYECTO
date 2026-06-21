package com.tienda.ms_productos.controller;

import com.tienda.ms_productos.dto.CategoriaDTO;
import com.tienda.ms_productos.model.Categoria;
import com.tienda.ms_productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias") // Agregamos el / inicial por buena práctica
public class CategoriaController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        // Roberto Marin: Listando categorías para el catálogo de productos
        return ResponseEntity.ok(service.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody CategoriaDTO dto) {
        // Benyamin Arcapio: Registro de categoría validado mediante DTO
        return new ResponseEntity<>(service.guardarCategoria(dto), HttpStatus.CREATED);
    }
}