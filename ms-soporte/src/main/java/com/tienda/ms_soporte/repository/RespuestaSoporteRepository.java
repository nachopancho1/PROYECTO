package com.tienda.ms_soporte.repository;

import com.tienda.ms_soporte.model.RespuestaSoporte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RespuestaSoporteRepository extends JpaRepository<RespuestaSoporte, Long> {
    List<RespuestaSoporte> findByTicketId(Long ticketId);
}