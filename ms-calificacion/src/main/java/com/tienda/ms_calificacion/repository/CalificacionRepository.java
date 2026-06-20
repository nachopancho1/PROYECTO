package com.tienda.ms_calificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.ms_calificacion.model.calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<alificacion, Long> {
}