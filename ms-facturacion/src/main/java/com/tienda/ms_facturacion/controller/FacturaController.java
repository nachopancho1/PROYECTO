package com.tienda.ms_facturacion.controller;
import com.tienda.ms_facturacion.model.Factura;
import com.tienda.ms_facturacion.dto.FacturaDTO;
import com.tienda.ms_facturacion.service.FacturacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/facturas")
@Tag(name = "1. Facturación", description = "Emisión y control contable")
public class FacturaController {
    @Autowired private FacturacionService service;

    @Operation(summary = "Listar todos los folios emitidos")
    @GetMapping public ResponseEntity<List<Factura>> getAll() { return ResponseEntity.ok(service.listarFacturas()); }

    @Operation(summary = "Emitir nueva factura electrónica")
    @PostMapping public ResponseEntity<Factura> create(@RequestBody FacturaDTO dto) { return ResponseEntity.ok(service.emitirFactura(dto)); }
}