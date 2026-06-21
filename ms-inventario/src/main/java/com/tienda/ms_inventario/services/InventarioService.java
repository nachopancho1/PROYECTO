package com.tienda.ms_inventario.services;

import com.tienda.ms_inventario.dto.*;
import com.tienda.ms_inventario.model.*;
import com.tienda.ms_inventario.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class InventarioService {

    @Autowired
    private MovimientoRepository movimientoRepo;

    @Autowired
    private BodegaRepository bodegaRepo;
    
    @Autowired
    private InventarioRepository inventarioRepo;

    // --- LÓGICA DE MOVIMIENTOS ---
    public List<Movimiento> listarMovimientos() {
        log.info("Roberto Marin: Consultando historial de movimientos de inventario en XAMPP");
        return movimientoRepo.findAll();
    }

    @Transactional
    public Movimiento registrarMovimiento(MovimientoDTO dto) {
        log.info("Benyamin Arcapio: Procesando {} de {} unidades para producto ID {}", 
                 dto.getTipoMovimiento(), dto.getCantidad(), dto.getProductoId());
        
        // Mapeo DTO -> Model
        Movimiento m = new Movimiento();
        m.setProductoId(dto.getProductoId());
        m.setTipoMovimiento(dto.getTipoMovimiento());
        m.setCantidad(dto.getCantidad());
        
        
        Bodega b = bodegaRepo.findById(dto.getBodegaId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));
        m.setBodega(b);

        return movimientoRepo.save(m);
    }

    // --- LÓGICA DE BODEGAS ---
    public List<Bodega> listarBodegas() {
        log.info("Roberto Marin: Listando todas las bodegas disponibles");
        return bodegaRepo.findAll();
    }

    @Transactional
    public Bodega guardarBodega(BodegaDTO dto) {
        log.info("Benyamin Arcapio: Creando nueva bodega: {}", dto.getNombre());
        
        Bodega b = new Bodega();
        b.setNombre(dto.getNombre());
        b.setUbicacion(dto.getUbicacion());
        
        return bodegaRepo.save(b);
    }

    public long obtenerCantidadEntradas() {
        log.info("Roberto Marin: Calculando el total de entradas");
        return movimientoRepo.contarTotalEntradas();
    }

    public long obtenerCantidadSalidas() {
        log.info("Roberto Marin: Calculando el total de salidas");
        return movimientoRepo.contarTotalSalidas();
    }
@Transactional
public Inventario actualizarStock(InventarioDTO dto) {
    log.info("Benyamin Arcapio: FORZANDO guardado manual en inventario...");
    
    try {
        Inventario i = new Inventario();
        
        i.setProductoId(dto.getProductoId());
        i.setCantidad(dto.getCantidad());
        i.setTipoMovimiento(dto.getTipoMovimiento());
        inventarioRepo.save(i);
                

        Inventario resultado = inventarioRepo.saveAndFlush(i);
        log.info("Benyamin Arcapio: Registro guardado con ID: {}", resultado.getId());
        return resultado;
    } catch (Exception e) {
        log.error("ERROR CRÍTICO AL GUARDAR: {}", e.getMessage());
        throw e;
    }
}
}
