package com.tienda.ms_soporte.repository;

import com.tienda.ms_soporte.model.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Long> {
    List<TicketSoporte> findByEstado(String estado);
}