package com.tienda.ms_pedidos.service;

import com.tienda.ms_pedidos.dto.*;
import com.tienda.ms_pedidos.model.*;
import com.tienda.ms_pedidos.repository.*;
import com.tienda.ms_pedidos.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Slf4j
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private DetallePedidoRepository detalleRepo;
    @Autowired private EnvioRepository envioRepo;

    public List<Pedido> listarPedidos() {
        log.info("Benyamin Arcapio: Iniciando consulta de lista de pedidos en XAMPP");
        return pedidoRepo.findAll();
    }

    @Transactional
    public Pedido registrarPedido(PedidoDTO dto) {
        log.info("Benyamin Arcapio: Procesando registro de pedido código: {}", dto.getCodigo());
        Pedido p = new Pedido();
        p.setCodigo(dto.getCodigo());
        p.setClienteId(dto.getClienteId());
        return pedidoRepo.save(p);
    }

    @Transactional
    public envio registrarEnvio(EnvioDTO dto) {
        log.info("Benyamin Arcapio: Generando orden de despacho para la dirección: {}", dto.getDireccionEnvio());
        Pedido p = pedidoRepo.findById(dto.getPedidoId())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Pedido no existe."));
        envio e = new envio();
        e.setDireccionEnvio(dto.getDireccionEnvio());
        e.setPedido(p);
        return envioRepo.save(e);
    }
    
    public List<DetallePedido> listarDetalles() { return detalleRepo.findAll(); }
}