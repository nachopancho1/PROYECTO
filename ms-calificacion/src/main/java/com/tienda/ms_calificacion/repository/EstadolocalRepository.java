package com.tienda.ms_calificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.ms_calificacion.model.estadolocal;

@Repository
public interface EstadolocalRepository extends JpaRepository<Estadolocal, Long> {
}