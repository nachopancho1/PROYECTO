package com.tienda.ms_ventas.services;

import com.tienda.ms_ventas.dto.*;
import com.tienda.ms_ventas.model.*;
import com.tienda.ms_ventas.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VentaService {

    @Autowired private VentaRepository ventaRepo;
    @Autowired private MetodoPagoRepository pagoRepo;
    @Autowired private DetalleVentaRepository detalleRepo;

    // --- LÓGICA DE MÉTODOS DE PAGO ---
    
    // CORRECCIÓN
    @Transactional
    public MetodoPago guardarMetodoPago(MetodoPagoDTO dto) {
        // Usamos dto.getTipo() que es lo que definiste en el Model
        log.info("Benyamin Arcapio: Registrando nuevo medio de pago: {}", dto.getTipo());
        
        MetodoPago mp = new MetodoPago();
        mp.setTipo(dto.getTipo()); // <--- Cambiado de setNombre a setTipo
        mp.setComprobante(dto.getComprobante()); // <--- Agregado
        
        return pagoRepo.save(mp);
    }

    public List<MetodoPago> listarMetodosPago() {
        log.info("Roberto Marin: Consultando todos los métodos de pago disponibles");
        return pagoRepo.findAll();
    }

    // --- PROCESAR UNA VENTA ---
    @Transactional
    public Venta procesarVenta(VentaDTO dto) {
        log.info("Roberto Marin: Iniciando transaccion de venta para cliente ID: {}", dto.getClienteId());

        MetodoPago mp = pagoRepo.findById(dto.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Metodo de pago no encontrado"));

        // 1. Creamos la cabecera de la venta
        Venta venta = new Venta();
        venta.setClienteId(dto.getClienteId());
        venta.setMetodoPago(mp);
        venta = ventaRepo.save(venta); // Guardamos para obtener el ID de venta

        // 2. Procesamos los detalles
        List<DetalleVenta> detallesModel = new ArrayList<>();

        for (DetalleVentaDTO detDto : dto.getDetalles()) {
            log.info("Benyamin Arcapio: Agregando producto ID {} a la venta", detDto.getProductoId());
            
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProductoId(detDto.getProductoId());
            detalle.setCantidad(detDto.getCantidad());
            
            detallesModel.add(detalle);
        }

        detalleRepo.saveAll(detallesModel);
        log.info("Roberto Marin: Venta completada exitosamente");
        
        return venta;
    }
}