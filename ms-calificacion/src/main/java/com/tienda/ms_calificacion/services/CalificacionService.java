package com.tienda.ms_calificacion.services;

import com.tienda.ms_calificacion.dto.CalificacionDTO; 
import com.tienda.ms_calificacion.dto.EstadolocalDTO;
import com.tienda.ms_calificacion.dto.OpinionDTO;
import com.tienda.ms_calificacion.model.*;
import com.tienda.ms_calificacion.repository.*;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class CalificacionService {

    @Autowired
    private CalificacionRepository califRepo;

    @Autowired
    private EstadolocalRepository estadoRepo;

    @Autowired
    private OpinionRepository opinRepo;

    // 1: Listar Calificaciones
    public List<Calificacion> listarCalificaciones() {
        log.info("Ignacio: Iniciando consulta de lista completa de calificaciones en XAMPP");
        return califRepo.findAll();
    }

    // 2: Obtener Calificación por ID
    public Calificacion obtenerCalificacionPorId(Long id) {
        log.info("Ignacio: Buscando calificacion especifica con ID: {}", id);
        return califRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Calificación no encontrada"));
    }

    // 3: Registrar Calificación (Usa CalificacionDTO)
    @Transactional
    public Calificacion registrarCalificacion(CalificacionDTO dto) {
        log.info("Ignacio: Procesando registro de nueva calificacion");
        
        Calificacion c = new Calificacion();
        // Cambiado a tus variables reales: estrellas y comentario
        c.setEstrellas(dto.getPuntaje()); 
        c.setComentario(dto.getComentario());
        
        return califRepo.save(c);
    }

    // 4: Listar Estados de los Locales
    public List<Estadolocal> listarEstadosLocales() {
        log.info("Ignacio: Iniciando consulta de estados de locales");
        return estadoRepo.findAll();
    }

    // 5: Registrar o Actualizar Estado Local (Usa EstadolocalDTO)
    @Transactional
    public Estadolocal registrarEstadoLocal(EstadolocalDTO dto) {
        log.info("Ignacio: Procesando registro de estado de local");
        
        Estadolocal e = new Estadolocal();
        // Cambiado a tu variable real: aspecto
        e.setAspecto(dto.getEstado()); 
        
        return estadoRepo.save(e);
    }

    // 6: Listar Opiniones
    public List<Opinion> listarOpiniones() {
        log.info("Ignacio: Iniciando consulta de todas las opiniones de la tienda");
        return opinRepo.findAll();
    }

    // 7: Registrar Opinión (Usa OpinionDTO)
    @Transactional
    public Opinion registrarOpinion(OpinionDTO dto) {
        log.info("Ignacio: Procesando registro de opinion manual");
        
        Opinion o = new Opinion();
        // Cambiado a tu variable real: descripcion
        o.setDescripcion(dto.getTexto()); 
        
        return opinRepo.save(o);
    }
}