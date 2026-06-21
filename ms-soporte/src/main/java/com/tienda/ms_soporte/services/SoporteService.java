package com.tienda.ms_soporte.services;

import com.tienda.ms_soporte.dto.*;
import com.tienda.ms_soporte.model.*;
import com.tienda.ms_soporte.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class SoporteService {

    @Autowired
    private TicketSoporteRepository ticketRepo;

    @Autowired
    private AgenteSoporteRepository agenteRepo;

    @Autowired
    private RespuestaSoporteRepository respuestaRepo;

    public List<TicketSoporte> listarTickets() {
        log.info("Roberto Marin: Consultando todos los tickets de soporte en XAMPP");
        return ticketRepo.findAll();
    }

    public List<TicketSoporte> listarTicketsPorEstado(String estado) {
        log.info("Roberto Marin: Consultando tickets con estado {}", estado);
        return ticketRepo.findByEstado(estado);
    }

    @Transactional
    public TicketSoporte crearTicket(TicketSoporteDTO dto) {
        log.info("Benyamin Arcapio: Creando ticket para cliente ID {}", dto.getClienteId());

        TicketSoporte t = new TicketSoporte();
        t.setClienteId(dto.getClienteId());
        t.setAsunto(dto.getAsunto());
        t.setDescripcion(dto.getDescripcion());
        t.setPrioridad(dto.getPrioridad());
        t.setEstado("ABIERTO");

        return ticketRepo.save(t);
    }

    @Transactional
    public TicketSoporte asignarAgente(Long ticketId, Long agenteId) {
        TicketSoporte t = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        AgenteSoporte a = agenteRepo.findById(agenteId)
                .orElseThrow(() -> new RuntimeException("Agente no encontrado"));

        log.info("Ignacio Alvarez: Asignando agente {} al ticket {}", a.getNombre(), t.getId());

        t.setAgente(a);
        t.setEstado("ASIGNADO");

        return ticketRepo.save(t);
    }

    @Transactional
    public TicketSoporte cerrarTicket(Long ticketId) {
        log.info("Benyamin Arcapio: Cerrando ticket ID {}", ticketId);

        try {
            TicketSoporte t = ticketRepo.findById(ticketId)
                    .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

            t.setEstado("CERRADO");
            return ticketRepo.saveAndFlush(t);
        } catch (Exception e) {
            log.error("ERROR CRITICO AL CERRAR TICKET: {}", e.getMessage());
            throw e;
        }
    }

    public List<AgenteSoporte> listarAgentes() {
        log.info("Roberto Marin: Consultando agentes de soporte registrados");
        return agenteRepo.findAll();
    }

    @Transactional
    public AgenteSoporte guardarAgente(AgenteSoporteDTO dto) {
        log.info("Benyamin Arcapio: Registrando agente de soporte {}", dto.getNombre());

        AgenteSoporte a = new AgenteSoporte();
        a.setNombre(dto.getNombre());
        a.setEmail(dto.getEmail());
        a.setEspecialidad(dto.getEspecialidad());

        return agenteRepo.save(a);
    }

    public List<RespuestaSoporte> listarRespuestas() {
        log.info("Roberto Marin: Consultando respuestas de soporte");
        return respuestaRepo.findAll();
    }

    public List<RespuestaSoporte> listarRespuestasPorTicket(Long ticketId) {
        log.info("Roberto Marin: Consultando respuestas del ticket {}", ticketId);
        return respuestaRepo.findByTicketId(ticketId);
    }

    @Transactional
    public RespuestaSoporte guardarRespuesta(RespuestaSoporteDTO dto) {
        TicketSoporte t = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        log.info("Benyamin Arcapio: Registrando respuesta para ticket {}", t.getId());

        RespuestaSoporte r = new RespuestaSoporte();
        r.setMensaje(dto.getMensaje());
        r.setTicket(t);

        return respuestaRepo.save(r);
    }
}   