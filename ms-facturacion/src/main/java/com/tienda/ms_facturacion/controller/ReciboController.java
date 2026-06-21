package com.tienda.ms_facturacion.controller;
import com.tienda.ms_facturacion.model.Recibo;
import com.tienda.ms_facturacion.dto.ReciboDTO;
import com.tienda.ms_facturacion.service.FacturacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/recibos")
@Tag(name = "2. Recibos", description = "Procesamiento de comprobantes")
public class ReciboController {
    @Autowired private FacturacionService service;

    @Operation(summary = "Cerrar flujo vinculando un método de pago")
    @PostMapping public ResponseEntity<Recibo> create(@RequestBody ReciboDTO dto) { return ResponseEntity.ok(service.procesarPagoRecibo(dto)); }
}