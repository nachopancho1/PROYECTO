package com.tienda.ms_pedidos.controller;
import com.tienda.ms_pedidos.model.envio;
import com.tienda.ms_pedidos.dto.EnvioDTO;
import com.tienda.ms_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/envios")
@Tag(name = "2. Envíos", description = "Gestión de despachos")
public class EnvioController {
    @Autowired private PedidoService service;

    @Operation(summary = "Registrar un despacho asociado a un pedido")
    @PostMapping public ResponseEntity<envio> create(@RequestBody EnvioDTO dto) { return ResponseEntity.ok(service.registrarEnvio(dto)); }
}