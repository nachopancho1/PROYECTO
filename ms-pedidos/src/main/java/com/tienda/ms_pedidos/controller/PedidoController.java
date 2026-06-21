package com.tienda.ms_pedidos.controller;

import com.tienda.ms_pedidos.model.Pedido;
import com.tienda.ms_pedidos.dto.PedidoDTO;
import com.tienda.ms_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "1. Pedidos", description = "Gestión modular de órdenes")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(summary = "Listar todos los pedidos")
    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(service.listarPedidos());
    }

    @Operation(summary = "Crear un nuevo pedido")
    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(service.registrarPedido(dto));
    }
}