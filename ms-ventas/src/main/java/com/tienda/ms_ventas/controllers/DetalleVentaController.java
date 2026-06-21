package com.tienda.ms_ventas.controllers;

import com.tienda.ms_ventas.model.DetalleVenta;
import com.tienda.ms_ventas.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/detallesventa")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaRepository repo;

    @GetMapping
    public List<DetalleVenta> listar() {
        return repo.findAll();
    }

    // con esto deberia poder poner el monto que yo quiera y con el buscar cuales esten por ese monto
    @GetMapping("/contarprecios/{monto}")
    public long contarPreciosAltos(@PathVariable Double monto) {
    return repo.contarVentasQueSuperanMonto(monto);
}
}