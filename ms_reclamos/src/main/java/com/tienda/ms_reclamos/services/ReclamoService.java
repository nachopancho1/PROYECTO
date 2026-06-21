package com.tienda.ms_reclamos.services;

import com.tienda.ms_reclamos.dto.ReclamoDTO;
import com.tienda.ms_reclamos.dto.HistorialDTO;
import com.tienda.ms_reclamos.dto.ResolucionDTO;
import com.tienda.ms_reclamos.model.Reclamo;
import com.tienda.ms_reclamos.model.Historial;
import com.tienda.ms_reclamos.model.Resolucion;
import com.tienda.ms_reclamos.repository.ReclamoRepository;
import com.tienda.ms_reclamos.repository.HistorialRepository;
import com.tienda.ms_reclamos.repository.ResolucionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReclamoService {

    @Autowired
    private ReclamoRepository reclamoRepo;

    @Autowired
    private HistorialRepository historialRepo;

    @Autowired
    private ResolucionRepository resolucionRepo;

  
    // 1: Listar Reclamos
    @Transactional(readOnly = true)
    public List<Reclamo> listarReclamos() {
        log.info("Ignacio: Listando todos los reclamos registrados");
        return reclamoRepo.findAll();
    }

    // 2: Registrar Reclamo (Usa ReclamoDTO)
    @Transactional
    public Reclamo registrarReclamo(ReclamoDTO dto) {
        log.info("Ignacio: Procesando registro de un nuevo reclamo");
        
        Reclamo r = new Reclamo();
        r.setAsunto(dto.getAsunto());
        r.setDetalle(dto.getDetalle());
        r.setCategoria(dto.getCategoria());
        r.setFechaRegistro(LocalDate.now()); // Asigna la fecha de hoy automáticamente
        
        return reclamoRepo.save(r);
    }

    // 3: Listar Historial de Estados
    @Transactional(readOnly = true)
    public List<Historial> listarHistorial() {
        log.info("Ignacio: Listando el historial de estados de los reclamos");
        return historialRepo.findAll();
    }

    // 4: Registrar Historial Estado (Usa HistorialEstadoDTO)
    @Transactional
    public Historial registrarHistorial(HistorialDTO dto) {
        log.info("Ignacio: Cambiando estado de un reclamo en el historial");
        
        Historial h = new Historial();
        h.setEstadoActual(dto.getEstadoActual());
        h.setNotasInternas(dto.getNotasInternas());
        h.setFechaActualizacion(LocalDate.now()); // Asigna la fecha de la actualización
        
        return historialRepo.save(h);
    }

    // 5: Listar Resoluciones
    @Transactional(readOnly = true)
    public List<Resolucion> listarResoluciones() {
        log.info("Ignacio: Listando todas las resoluciones formales");
        return resolucionRepo.findAll();
    }

    // 6: Registrar Resolución (Usa ResolucionDTO)
    @Transactional
    public Resolucion registrarResolucion(ResolucionDTO dto) {
        log.info("Ignacio: Registrando respuesta y solución final para el cliente");
        
        Resolucion res = new Resolucion();
        res.setRespuestaOfrecida(dto.getRespuestaOfrecida());
        res.setRequiereCompensacion(dto.isRequiereCompensacion());
        res.setFechaResolucion(LocalDate.now()); // Asigna la fecha de resolución automáticamente
        
        return resolucionRepo.save(res);
    }
}