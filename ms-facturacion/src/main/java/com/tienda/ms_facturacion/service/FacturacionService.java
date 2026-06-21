package com.tienda.ms_facturacion.service;

import com.tienda.ms_facturacion.dto.*;
import com.tienda.ms_facturacion.model.*;
import com.tienda.ms_facturacion.repository.*;
import com.tienda.ms_facturacion.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Slf4j
public class FacturacionService {

    @Autowired private FacturaRepository facturaRepo;
    @Autowired private ImpuestoRepository impuestoRepo;
    @Autowired private ReciboRepository reciboRepo;

    public List<Factura> listarFacturas() {
        log.info("Benyamin Arcapio: Obteniendo historial relacional de facturación");
        return facturaRepo.findAll();
    }

    @Transactional
    public Factura emitirFactura(FacturaDTO dto) {
        log.info("Benyamin Arcapio: Emitiendo documento tributario legal N°: {}", dto.getNumeroFactura());
        Factura f = new Factura();
        f.setNumeroFactura(dto.getNumeroFactura());
        f.setTotal(dto.getTotal());
        return facturaRepo.save(f);
    }

    @Transactional
    public Recibo procesarPagoRecibo(ReciboDTO dto) {
        log.info("Benyamin Arcapio: Asociando comprobante de pago electrónico al documento ID: {}", dto.getFacturaId());
        Factura f = facturaRepo.findById(dto.getFacturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Error: La factura tributaria no existe."));
        Recibo r = new Recibo();
        r.setMetodoPago(dto.getMetodoPago());
        r.setFactura(f);
        return reciboRepo.save(r);
    }

    public List<Impuesto> listarImpuestos() { return impuestoRepo.findAll(); }
}